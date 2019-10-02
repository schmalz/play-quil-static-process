(ns play-quil-static-process.core
  (:require [quil.core :as q]
            [play-quil-static-process.dynamic :as d])
  (:gen-class))

(q/defsketch example
  :title "sketch"
  :setup d/setup
  :draw d/draw
  :size [1800 1800])

(defn refresh []
  (use :reload 'play-quil-static-process.dynamic)
  (.loop example)) ; Force a redraw.
