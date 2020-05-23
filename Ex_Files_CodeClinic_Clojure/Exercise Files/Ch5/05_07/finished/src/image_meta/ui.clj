(ns image-meta.ui
  (:require [image-meta.backend :as backend]
            [seesaw.core :as ss]
            [seesaw.mig :as mig]
            [seesaw.chooser :as sc]
            [clojure.java.io :as io]
            [clojure.string :as str])
  (:gen-class))

(def main-panel
  (mig/mig-panel
    :constraints ["wrap 4, center, gap 15"]
    :items [[(ss/label :icon (io/resource "icons/folder_open_16.png")
                       :id :src :text "Choose source directory") "span 4"]
            [(ss/label :icon (io/resource "icons/folder_open_16.png")
                       :id :target :text "Choose target directory") "span 4"]
            [(ss/checkbox :id :copy :selected? true) "span 1"]
            [(ss/label :text "Copy files?") "span 3"]
            [""]
            [(ss/button :id :submit :text "Submit" :enabled? false)]]))

(defn build-main-frame
  [exit?]
  (ss/frame :title "Image Organizer"
            :size [500 :by 200]
            :on-close (if exit? :exit :dispose)
            :content main-panel
            :resizable? false))

(defn ui-main
  [exit?]
  (ss/invoke-later
    (-> (build-main-frame exit?)
        ss/show!)))

(defn main
  []
  (ui-main true))
