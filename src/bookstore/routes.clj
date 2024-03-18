(ns bookstore.routes
  (:require [compojure.core :refer [defroutes GET POST]]
            [compojure.route :as route]   
            [bookstore.controller :as controller]
           
            )
  )



(def route-1
  (POST "/bookstore-query" request controller/sql-handler))

(def route-2
  (POST "/bookstore-query-with-md" request controller/sql-handler-2))

(def route-3
  (GET "/hello" request controller/hello-handler))

(def route-4
  (GET "/string" request controller/all-persons))

(def route-5
  (GET "/data-structure" request {:body {:a 1
                                         :b #{2 3 4}
                                         :c "nested data structure"}}))

(def route-6
  (GET "/all-person" request controller/all-persons))


(def route-7
  (GET "/all-person-count" request controller/all-persons-count))


(def route-8
  (GET "/find-person" request controller/find-person-handler))


(defroutes routes
  route-1
  route-2
  route-3
  route-4
  route-5
  route-6
  route-7
  route-8
  (route/not-found "Not the route you are looking for"))