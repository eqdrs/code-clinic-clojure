(ns image-meta.files
  (:require [clojure.java.io :as io]))

(defn find-files
  [file-dir]
  (->> (io/file file-dir)
       file-seq
       (remove #(or (.isDirectory %)
                    (.isHidden %)))))