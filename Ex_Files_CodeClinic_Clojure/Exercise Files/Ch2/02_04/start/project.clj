(defproject thumb "0.1.0"
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/math.combinatorics "0.1.3"]
                 [opencv/opencv-native "2.4.13"]
                 [opencv/opencv "2.4.13"]]
  :main thumb.core
  :plugins [[lein-localrepo "0.5.2"]]
  :uberjar-name "thumb.jar"
  :profiles {:uberjar {:aot :all}})


