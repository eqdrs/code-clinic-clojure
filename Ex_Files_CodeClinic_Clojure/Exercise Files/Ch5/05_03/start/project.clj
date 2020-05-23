(defproject image-meta "0.1"
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [com.drewnoakes/metadata-extractor "2.8.1"]
                 [seesaw "1.4.5"]]
  :main image-meta.ui
  :uberjar-name "image-meta.jar"
  :source-paths ["src"]
  :profiles {:uberjar {:aot :all}})
