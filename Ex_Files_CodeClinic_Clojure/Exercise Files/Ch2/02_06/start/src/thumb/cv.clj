(ns thumb.cv
  (:require [clojure.java.io :as io])
  (:import [org.opencv.core Core Mat]
           [org.opencv.highgui Highgui]
           [org.opencv.imgproc Imgproc]
           (clojure.lang RT)))

(defonce opencv-native-loaded?
         (RT/loadLibrary Core/NATIVE_LIBRARY_NAME))

(defn load-image
  [path]
  (Highgui/imread path))

(defn get-image-meta
  [mat]
  (let [rows (.rows mat)
        cols (.cols mat)
        type (.type mat)]
    {:rows rows :cols cols :type type}))

(defn diff-dimensions
  [a b]
  (let [a-meta (get-image-meta a)
        b-meta (get-image-meta b)]
    [(- (:cols a-meta) (:cols b-meta))
     (- (:rows a-meta) (:rows b-meta))]))

(defn find-src-templ
  [file-a file-b]
  (let [[a b] (map load-image [file-a file-b])
        [cols rows] (diff-dimensions a b)]
    (cond
      (and (<= cols 0) (<= rows 0)) [a b]
      (and (> cols 0) (> rows 0)) [b a]
      :else [nil nil])))

(defn match-template [src templ]
  (let [[rows cols] (diff-dimensions src templ)
        result-mat (Mat. (inc (Math/abs rows)) (inc (Math/abs cols)) (.type src))]
    (Imgproc/matchTemplate src templ result-mat Imgproc/TM_CCOEFF_NORMED)
    result-mat))

