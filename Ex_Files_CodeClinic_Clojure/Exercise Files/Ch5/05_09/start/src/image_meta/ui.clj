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

(def ui-data (atom {:copy true}))

(defn check-enable-submit!
  [frame]
  (let [{:keys [src target]} @ui-data]
    (when (and src target)
      (ss/config! (ss/select frame [:#submit]) :enabled? true))))

(defn choose-dir
  [frame key]
  (when-let [chosen-dir (sc/choose-file :selection-mode :dirs-only :dir "resources")]
    (let [abs-path (.getAbsolutePath chosen-dir)
          dir-name (.getName chosen-dir)
          selector-key (if (= key :src) :#src :#target)]
      (ss/config! (ss/select frame [selector-key]) :text dir-name)
      (swap! ui-data assoc key abs-path)
      (check-enable-submit! frame))))

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
