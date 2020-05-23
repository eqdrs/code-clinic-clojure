(ns queens.permutation
  (:require [clojure.math.combinatorics :as combo]))

(defn dist [a b]
  (Math/abs (- a b)))

(defn diagonal-attack-coords?
  [[x1 y1] [x2 y2]]
  (= (dist x1 x2)
     (dist y1 y2)))

(defn has-diagonal-attack?
  [board]
  (let [coords (map-indexed vector board)
        pairings (combo/combinations coords 2)
        attacks (map (fn [[a b]] (diagonal-attack-coords? a b)) pairings)]
    (some true? attacks)))

(defn generate
  [n]
  (remove has-diagonal-attack?
    (combo/permutations (range n))))