(ns bookstore.core
  (:require  
    [bookstore.routes :refer [routes]]
    [clojure.tools.logging :as log]
    [ring.adapter.jetty :refer [run-jetty]]
    [muuntaja.middleware :as middleware]
   )
  (:gen-class)
  )

(comment 
  
(def app (run-jetty routes {
                                        :port 8080 
                                        :join? false
                                        }
                    )
  )

)


(defn run []  
  (run-jetty (middleware/wrap-format routes) {
                               :port  8080 
                               :join? false
                              }
             )
  )


;(def app (run))


;(def stop (.stop app))    ;stop jetty server



(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (log/info "Hello, World!"))
