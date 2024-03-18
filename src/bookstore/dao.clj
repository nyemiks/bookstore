(ns bookstore.dao
  (:require
   [clojure.java.jdbc :as jdbc]
   [hikari-cp.core :as hikari]
   [clojure.string :as str]
   [clojure.tools.logging :as log])
  )




(def db {:dbtype "derby"
         :dbname  "c:\\Users\\nyemi\\Documents\\javadb\\deiteldb"})


(def db-ds {:datasource (hikari/make-datasource {:jdbc-url "jdbc:derby:c:\\Users\\nyemi\\Documents\\javadb\\deiteldb"})})



(defn fetch-data
  "fetches data from database using the query provided.
   'sql' query to be executed."
  [sql]
  (log/info "-- fetch-data -- ")
 ; (log/info "sql: " sql)
  (log/info "sql: " sql)
  (into [] (jdbc/query db-ds [sql])))

(defn fetch-data
  "fetches data from database using the query provided.
   'sql' query to be executed."
  [sql & args]
  (log/info "-- fetch-data -- ")
  (log/info "sql: " sql)
  (log/info "args: " args)

  (let
   [args-array (into [] args)
    params (concat [sql] args-array)]
    (log/info "params: " params)
    (into [] (jdbc/query db-ds params))))


(defn- get-column-md
  "extracts the meta data at the column index 
  'context' {:rsmd rsmd :query-md []}
   'item' index
   "
  [context item]
  (let
   [rsmd (:rsmd context)
    col-type-name  (.getColumnTypeName rsmd item)
    col-name  (.getColumnName rsmd item)]
    (update context :query-md conj [col-name col-type-name])))



(defn get-result-md
  "fetches the result meta data for the giving query.
   'sql' query to be executed."
  [sql]
  (log/info " -- get-result-md -- ")
  (log/info " sql: " sql)
  (let
   [conn (jdbc/get-connection db-ds)
    stmt   (.createStatement conn)
    resultset  (.executeQuery stmt sql) ; result set
    rsmd  (.getMetaData resultset)  ; result set meta data
    col-count  (.getColumnCount rsmd)
    col-idx    (range 1 (inc col-count))
    acc   (hash-map :rsmd rsmd :query-md []) ; accumulator or initial context

    context  (reduce get-column-md acc col-idx)
    query-meta-data  (:query-md context)]
    (.close resultset)  ;; close jdbc result set
    (log/info "column Count: " col-count)
       ; (log/info "context: " context)
    (log/info "query meta data: " query-meta-data)
    query-meta-data))

(defn get-table-metadata [table]
  (log/info " -- get-table-metadata -- ")
  (log/info " table: " table)
  (let [conn (jdbc/get-connection db-ds)]
    (->
     conn
     (.getMetaData)
     (.getColumns nil nil (str/upper-case table) nil)
     (jdbc/metadata-result))))

(defn all-tables-metadata []
  (log/info " -- all-tables-metadata -- ")
  (let [conn (jdbc/get-connection db-ds)]
    (->
     conn
     (.getMetaData)
     (.getTables nil nil nil (into-array ["TABLE"]))
     (jdbc/metadata-result))))


(defn execute-query
  "execute the supplied query. fetches the
   query result. also returns the query metadata.
   'sql' query to be executed."
  [sql]
  (log/info " -- execute query -- ")
  (log/info " execute query: " sql)
  {:query-md (get-result-md sql)
   :query-data (fetch-data sql)})


(defn fetch-all-people
  "fetches all persons"
  []
  (fetch-data "select * from addresses"))


(defn fetch-by-lastname
  "fetch persons by lastname"
  [lastname]
  (log/info " -- fetch by lastname -- ")
  (fetch-data "SELECT * FROM Addresses WHERE LastName = ?" lastname))

(comment

  ;note: when converting
  ; json -> edn

  ; the edn might have to be keywordized

  ; in json format a record
 ; {
  ;"FIRSTNAME": "ike",
  ; "LASTNAME":  "onukwu",
  ;  "EMAIL":    "ike@gmail.com",
   ; "PHONE":    "08027360308"
 ; }

  ; in edn format a record
  {:FIRSTNAME "ike"
   :LASTNAME   "onukwu"
   :EMAIL    "ike@gmail.com"
   :PHONE     "08027360308"}

  or

  {:firstname "Glory"
   :lastname   "Alozie"
   :email    "galozie@gmail.com"
   :phone     "08039300308"})

(defn new-person
  "Returns a person contact"
  [firstname lastname email phone]
  {:firstname firstname
   :lastname  lastname
   :email     email
   :phone     phone})

(defn insert-person
  "insert person.
   'person' record (a map)"
  [person]
  (log/info " -- insert-people -- ")
  (let
   [insert-sql (str/join " "
                         ["INSERT INTO Addresses "
                          "( FirstName, LastName, Email, PhoneNumber ) "
                          "VALUES ( ?, ?, ?, ? )"])]
    (log/info "sql: " insert-sql)
    (log/info " person record: " person)

    (jdbc/insert! db-ds :addresses person)))