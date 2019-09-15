(ns play-quil-static-process.dynamic
  (:require [clojure.pprint :as pp]
            [quil.core :as q]))

(defn- save-file-name
  []
  (pp/cl-format nil
                "~d-~2,'0d-~2,'0d-~2,'0d-~2,'0d-~2,'0d.tif"
                (q/year) (q/month) (q/day) (q/hour) (q/minute) (q/seconds)))

(defn setup
  []
  (q/color-mode :hsb 360 100 100 1.0))

(defn- paint-triangle
  "Paint a triangle."
  [x y alpha offset]
  (q/fill 68 100 60 alpha)
  (q/triangle (- x offset) y x y x (+ y offset)))

(defn- paint-triangle-set
  "Paint a congruent set of three triangles."
  [x y]
  (dorun (map #(paint-triangle x y %1 %2) [0.25 0.5 0.75] [200 150 100])))

(defn draw
  []
  (q/no-loop)
  (q/no-stroke)
  (q/background 44 10 99)
  (dotimes [_ 10]
    (paint-triangle-set (q/random (q/width)) (q/random (q/height))))
  (q/save (save-file-name)))
