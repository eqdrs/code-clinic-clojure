(ns theremin.core
  (:require [quil.core :as q]
            [quil.middleware :as m])
  (:gen-class))

(def width 600)

(def height 600)

(defn setup []
  (q/frame-rate 60)
  (q/smooth)
  (q/color-mode :hsb)
  {:x 0
   :y 0
   :pressed false
   :count 0})

(defn update-state [state]
  {:x (q/mouse-x)
   :y (q/mouse-y)
   :pressed (q/mouse-pressed?)
   :count 0})

(defn draw-state
  [{:keys [x y pressed count]}]
  (let [perc-x (/ x width)
        perc-y (/ (- height y) height)
        color (mod (* perc-y 255 6) 255)]
    (if pressed
      (do
        (q/fill color 255 255)
        (q/ellipse x y 30 30))
      (q/background 255))))

(defn sketch-main
  [exit?]
  (q/sketch
    :title "Theremin"
    :size [width height]
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