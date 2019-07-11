(ns play-quil-static-process.dynamic
  (:require [quil.core :as q]))

(defn setup
  []
  (q/color-mode :hsb 360 100 100 1.0))

(defn draw
  []
  (q/no-loop)
  (q/no-fill)
  (q/background 193 100 21)
  (q/stroke 237 47 77)
  (dotimes
   [n 80]
   (let [x (* 10 (inc n))
         y x]
     (q/triangle x y x (+ y 10) (+ x 10) y)))
  (q/save "sketch.tif"))
