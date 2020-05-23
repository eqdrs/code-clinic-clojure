(ns web.core
  (:require [clojure.data.csv :as csv]
            [net.cgrand.enlive-html :as enl]
            [clojure.java.io :as io]
            [clojure.string :as str])
  (:gen-class))

(defn parse-csv-file
  [filename]
  (-> (slurp filename)
      csv/read-csv))

(defn find-csv-files
  []
  (->> (io/file "resources/website/_assets/")
       file-seq
       (remove #(.isDirectory %))))

(defn load-csvs
  []
  (map parse-csv-file (find-csv-files)))
