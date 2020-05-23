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

(defn transform-row
  [row-str]
  (let [row-data (str/split row-str #"\s+")
        [date time air-temp baro _ _ _ _ wind-speed] row-data
        dt-ms (iso8601->ms (str date "T" time))
        [air-temp baro wind-speed] (map #(Double/parseDouble %) [air-temp baro wind-speed])]
    {:recorded_at dt-ms
     :air_temp air-temp
     :baro baro
     :wind_speed wind-speed}))