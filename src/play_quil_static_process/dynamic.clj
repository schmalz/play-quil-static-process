(ns play-quil-static-process.dynamic
  (:require [clojure.pprint :as pp]
            [quil.core :as q]))

(def ^:private colour [337 97 60.4])

(def ^:private alphas [0.25 0.3 0.35])

(def ^:private offsets [200 150 100])

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
  ;(q/fill 356 98.3 46.1 alpha)
  (apply q/fill (conj colour alpha))
  (q/triangle (- x offset) y x y x (+ y offset)))

(defn- paint-triangle-set
  "Paint a congruent set of three triangles."
  [x y]
  (dorun (map #(paint-triangle x y %1 %2) alphas offsets)))

(defn draw
  []
  (q/no-loop)
  (q/no-stroke)
  (q/background 0 0 95)
  (dotimes [_ 10]
    (paint-triangle-set (q/random (q/width)) (q/random (q/height))))
  (q/save (save-file-name)))
