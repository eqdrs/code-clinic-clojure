(ns queens.core-test
  (:require [clojure.test :refer [deftest is]]
            [queens.permutation :as perm]
            [queens.inductive :as ind]))

(def four-queens-solutions #{[1 3 0 2] [2 0 3 1]})

(deftest test-generate
  (is (= four-queens-solutions (set (perm/generate 4))))
  (is (= four-queens-solutions (set (ind/generate 4))))
  (is (= (set (perm/generate 8)) (set (ind/generate 8)))))