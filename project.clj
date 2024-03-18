(defproject bookstore "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [
                  [org.clojure/clojure "1.11.1"]
                  [org.clojure/java.jdbc "0.7.9"]
                  [org.apache.derby/derby "10.14.2.0"]
                  [hikari-cp "2.8.0"]
                  [ring/ring-core "1.7.1"]
                  [ring/ring-jetty-adapter "1.7.1"]
                  [org.clojure/tools.logging "1.3.0"]
                  [compojure "1.6.1"]
                  [metosin/muuntaja "0.6.4"]
                 ; [org.clojure/data.json "0.2.6"] 
                  [org.slf4j/slf4j-api "1.7.25"]
                  [org.slf4j/slf4j-jdk14 "1.7.25"]                 
               ;  [org.apache.logging.log4j/log4j-api "2.20.0"]
              ;   [org.apache.logging.log4j/log4j-core "2.20.0"]
               ;  [org.apache.logging.log4j/log4j-slf4j-impl "2.20.0"]
                 ]
  :main ^:skip-aot bookstore.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all
                       :jvm-opts ["-Dclojure.compiler.direct-linking=true" 
                                ;  "-Dclojure.tools.logging.factory=clojure.tools.logging.impl/slf4j-factory"
                                   "-Dclojure.tools.logging.factory=clojure.tools.logging.impl/jul-factory"
                                ;  "-Dclojure.tools.logging.factory=clojure.tools.logging.impl/log4j2-factory"
                                  ]
                       }})
