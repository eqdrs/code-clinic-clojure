(defproject theremin "0.1"
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [quil "2.4.0"]
                 [jfugue "4.0.3"]]
  :main theremin.core
  :uberjar-name "theremin.jar"
  :profiles {:uberjar {:aot :all}})
