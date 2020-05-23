(ns theremin.core
  (:require [quil.core :as q]
            [quil.middleware :as m])
  (:gen-class))

(defn setup [] {})

(defn update-state [state]
  state)

(defn draw-state [state])

(defn sketch-main
  [exit?]
  (q/sketch
    :title "Theremin"
    :size [600 600]
    :setup setup
    :update update-state
    :draw draw-state
    :features (if exit?
                [:keep-on-top :exit-on-close]
                [:keep-on-top])
    :middleware [m/fun-mode]))

(defn -main
  []
  (sketch-main true))