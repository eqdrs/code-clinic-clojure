(ns queens.core
  (:require [queens.inductive :refer [generate]]
            [clojure.string :as str])
  (:gen-class))

(defn board-display
  [board]
  (let [by-rows (map second (sort (map-indexed (fn [x y] [y x]) board)))
        n (count board)
        row-str (fn [value]
                  (let [background (str/join (take n (repeat "-")))]
                    (str (subs background 0 value)
                         "Q"
                         (subs background (inc value) n))))
        board-strs (map row-str by-rows)]
    (doseq [row board-strs]
      (println row))))

(defn display-results
  [n]
  (doseq [[i board] (map-indexed vector (generate n))]
    (println "Board:  " (inc i))
    (board-display board)))

(defn -main
  ([]
   (println "Usage: Must supply a board size"))
  ([n]
   (let [n (Integer. n)]
     (println "Displaying solutions for boards of size " n)
     (display-results n))))