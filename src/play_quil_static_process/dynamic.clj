(ns play-quil-static-process.dynamic
  (:require [clojure.pprint :as pp]
            [quil.core :as q]))

(def ^:private yellow [45 100 71])
(def ^:private orange [8 89 80])
(def ^:private red [1 79 86])
(def ^:private magenta [331 74 83])
(def ^:private violet [237 45 77])
(def ^:private blue [205 82 82])
(def ^:private cyan [175 74 63])
(def ^:private green [68 100 60])

(def ^:private alphas [0.25 0.3 0.35])

(def ^:private phi (/ (+ 1 (q/sqrt 5)) 2))

(def ^:private x-offsets [(* phi 200) (* phi 150) (* phi 100)])
(def ^:private y-offsets [200 150 100])

(defn- save-file-name
  []
  (pp/cl-format nil
                "~d-~2,'0d-~2,'0d-~2,'0d-~2,'0d-~2,'0d.tif"
                (q/year) (q/month) (q/day) (q/hour) (q/minute) (q/seconds)))

(defn setup
  []
  (q/color-mode :hsb 360 100 100 1.0))

(defn- point-on
  "A random point on a line."
  [x1 y1 x2 y2]
  (q/stroke-weight 10)
  (let [t (q/random 1)]
    (q/point (+ x1 (* t (- x2 x1))) (+ y1 (* t (- y2 y1))))))

(defn- paint-line
  "Paint a line."
  [x1 y1 x2 y2]
  (q/stroke 0 0 25 0.75)
  (q/stroke-weight 5)
  (q/line x1 y1 x2 y2)
  (let [f (partial point-on x1 y1 x2 y2)]
    (dorun (repeatedly 11 f))))

(defn- paint-triangle
  "Paint a triangle."
  [x y alpha x-offset y-offset]
  (apply q/fill (conj green alpha))
  ; TODO use the golden ratio for the two sides
  (q/triangle (- x x-offset) y x y x (+ y y-offset)))

(defn- paint-triangle-set
  "Paint a congruent set of three triangles."
  [x y]
  (dorun (map #(paint-triangle x y %1 %2 %3) alphas x-offsets y-offsets)))

(defn draw
  []
  (q/no-loop)
  (q/no-stroke)
  (q/background 44 10 99)
  (dotimes [_ 11]
    (paint-triangle-set (q/random (q/width))
                        (q/random (q/height))))
  (dotimes [_ 5]
    (paint-line (q/random (q/width))
                (q/random (q/height))
                (q/random (q/width))
                (q/random (q/height))))
  (q/save (save-file-name)))
