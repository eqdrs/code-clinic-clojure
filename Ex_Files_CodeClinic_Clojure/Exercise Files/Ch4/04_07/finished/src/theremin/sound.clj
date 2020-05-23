(ns theremin.sound
  (:import [org.jfugue Player]))

(defonce player (Player.))

(defn play-str
  [pattern-str]
  (future (.play player pattern-str)))

(defn play-note
  [vol n]
  (play-str (format "I[Voice] X[Volume]=%d [%d]" vol n)))