(ns lakepend.backend
  (:require [clojure.string :as str]
            [clj-time.core :as t]
            [clj-time.format :as tf]
            [clj-time.coerce :refer [to-long from-long]]
            [lakepend.db :as db]
            [lakepend.request :refer [fetch-seq-for-year]]))

(defn datetime-str->ms
  [format-str v]
  (let [format (tf/formatter format-str)
        dt (tf/parse format v)]
    (to-long dt)))

(defn cal->ms
  [v]
  (try
    (datetime-str->ms "MMM/dd/yyyy" v)
    (catch IllegalArgumentException _ nil)))

(defn iso8601->ms
  [v]
  (datetime-str->ms "yyyy_MM_dd'T'HH:mm:ss" v))