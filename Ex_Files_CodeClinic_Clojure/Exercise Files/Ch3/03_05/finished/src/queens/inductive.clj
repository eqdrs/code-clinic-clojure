(ns queens.inductive
  (:require [clojure.set :as set]))

(defn dist [a b]
  (Math/abs (- a b)))

(defn diagonal-attack-coords?
  [[x1 y1] [x2 y2]]
  (= (dist x1 x2)
     (dist y1 y2)))

(defn has-diagonal-attack?
  [board]
  (let [column (dec (count board))
        row (last board)
        attacks (map-indexed
                  (fn [x y]
                    (diagonal-attack-coords? [x y] [column row])
                  (butlast board)))]
    (some true? attacks)))

(defn next-boards
  [n board]
  (map #(conj board %)
    (set/difference (set (range n)) (set board))))

(defn next-valid-boards
  [n board]
  (remove has-diagonal-attack? (next-boards n board)))