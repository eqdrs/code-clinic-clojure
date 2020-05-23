(ns lakepend.request
  (:require [clojure.java.io :as io]
            [clj-http.client :as http]))

(defn build-url
  [year]
  (str "https://lpo.dt.navy.mil/data/DM/Environmental_Data_Deep_Moor_"
       year ".txt"))

(defn fetch-for-year
  [year]
  (let [url (build-url year)]
    (try
      (http/get url {:insecure true :as :stream})
      (catch Exception _ nil))))

(defn fetch-seq-for-year
  [year]
  (some->> (fetch-for-year year)
           :body
           io/reader
           line-seq
           (drop 1)))