(ns thumb.core
  (:require [clojure.java.io :as io]
            [clojure.string :as str]
            [clojure.math.combinatorics :as combo]
            [thumb.cv :as cv])
  (:gen-class))

(defn find-image-files
  []
  (->> (io/file "resources/images")
       file-seq
       (map #(.getPath %))
       (filter #(str/ends-with? % ".jpg"))))

(defn run-match
  [[file-a file-b]]
  (println ".")
  [(cv/best-match file-a file-b) file-a file-b])

(defn run-pairings
  []
  (let [image-files (find-image-files)
        pairings (combo/combinations image-files 2)]
    (pmap run-match pairings)))

(defn find-matches
  []
  (let [results (vec (run-pairings))]
    (filter #(:match? (first %)) results)))

(defn -main
  []
  (let [matches (find-matches)]
    (println "Matches found:")
    (doseq [[result file-a file-b] matches]
      (println file-a "and" file-b))
    (shutdown-agents)))