(defproject queens "0.1"
  :main queens.core
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/math.combinatorics "0.1.3"]]
  :uberjar-name "queens.jar"
  :profiles {:uberjar {:aot :all}})

