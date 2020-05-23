(defproject lakepend "0.1"
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/java.jdbc "0.5.8"]
                 [org.xerial/sqlite-jdbc "3.8.11.2"]
                 [clj-http "2.1.0"]
                 [clj-time "0.11.0"]]
  :main lakepend.core
  :uberjar-name "lakepend.jar"
  :profiles {:uberjar {:aot :all}})
