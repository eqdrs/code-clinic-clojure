(ns image-meta.exif
  (:require [clojure.java.io :as io])
  (:import [com.drew.imaging ImageMetadataReader ImageProcessingException]))

(defn- extract-from-tags
  [tags]
  (into {} (map (fn [t] {(.getTagName t) (.getDescription t)}) tags)))

(defn- exif-from-filename
  [filename]
  (->> (io/file filename)
       ImageMetadataReader/readMetadata
       .getDirectories
       (map #(.getTags %))
       (map extract-from-tags)
       (into {})))

(defn safe-exif-from-filename
  [filename]
  (try (exif-from-filename filename)
       (catch ImageProcessingException _ {})))

(defn caption-from-filename
  [filename]
  (get (safe-exif-from-filename filename) "Caption/Abstract"))
