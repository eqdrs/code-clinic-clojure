(ns image-meta.backend
  (:require [image-meta.exif :as exif]
            [image-meta.files :as fs]
            [clojure.string :as str]
            [clojure.java.io :as io]))

(defn find-target-filenames
  [target-dir files]
  (let [filenames (map #(.getName %) files)
        captions (map exif/caption-from-filename files)
        prefixes (map #(str/upper-case ((fnil first "_") %)) captions)]
    (map #(str/join "/" [target-dir %1 %2]) prefixes filenames)))

(defn- find-filename-mapping
  [source-dir target-dir]
  (let [files (fs/find-files source-dir)
        target-filenames (find-target-filenames target-dir files)]
    (zipmap files target-filenames)))

(defn arrange-images
  [copy? source-dir target-dir]
  (doseq [[source-f target-filename] (find-filename-mapping source-dir target-dir)]
    (io/make-parents target-filename)
    (io/copy source-f (io/file target-filename))
    (when-not copy?
      (io/delete-file source-f))))