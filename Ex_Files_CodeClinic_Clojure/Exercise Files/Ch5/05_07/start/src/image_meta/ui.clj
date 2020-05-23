(ns image-meta.ui
  (:require [image-meta.backend :as backend]
            [seesaw.core :as ss]
            [seesaw.mig :as mig]
            [seesaw.chooser :as sc]
            [clojure.java.io :as io]
            [clojure.string :as str])
  (:gen-class))

(defn build-main-frame
  [exit?]
  (ss/frame :title "Image Organizer"
            :size [500 :by 200]
            :on-close (if exit? :exit :dispose)
            :resizable? false))

(defn ui-main
  [exit?]
  (ss/invoke-later
    (-> (build-main-frame exit?)
        ss/show!)))

(defn main
  []
  (ui-main true))
