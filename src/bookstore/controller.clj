(ns bookstore.controller
  (:require [bookstore.dao :refer [execute-query fetch-all-people fetch-data fetch-by-lastname]]
            [clojure.pprint :as pprint]
            [clojure.tools.logging :as log]
            [ring.util.response :as ring-response]
             [ring.middleware.params :as rmp]
            [ring.middleware.keyword-params :as rmkp]
            )
)



(defn hello-handler [request]
  (log/info "-- hello handler -- ")
  (log/info "request: " request)
  (pprint/pprint request)
  {:status 200
   :body "Hello World"}
  )


(defn sql-handler [request]
  (log/info " -- sql-handler -- ")
  ;(pprint/pprint request)
  (let [
        {:keys [body-params]} request
          {sql :sql} body-params
        ]
     (log/info "request body: " body-params)
     (log/info "sql: " sql)    
    {
     :status 200
     :body (fetch-data sql)
     }
    )
  )


(defn sql-handler-2 [request]
  (log/info " -- sql-handler-2 -- ")
  ;(pprint/pprint request)
  (let [
        {:keys [body-params]} request
        {sql :sql} body-params
        ]
    (log/info "request body: " body-params)
    (log/info "sql: " sql)
    {
     :status 200
     :body (execute-query sql)
     }
    )
  )




(defn all-persons [request]
  (let
       []
       (ring-response/response (fetch-all-people))
    )
  )


(defn all-persons-count [request]
  (let 
   [
    record-count (.toString (count (fetch-all-people)) )
   ]
    (log/info "record count: " record-count)

     (ring-response/response record-count)
    )
  )


(defn find-person [request]
   (log/info " -- find-person -- ")
    (pprint/pprint request)
  (let [{:keys [query-string params]} request 
         {:keys [lastname]}  params
        ]
    (log/info "query-string: " query-string)
    (log/info "params: " params)
    (log/info "lastname: " lastname)
   
    {
     :status 200
     :body (fetch-by-lastname lastname)
     }
    )
  )



; uses  the wrap-params and wrap-keyword-params middle ware to convert params to keyword for u
(def find-person-handler (-> find-person
                             rmkp/wrap-keyword-params
                             rmp/wrap-params)
  )