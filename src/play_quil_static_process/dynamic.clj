(ns play-quil-static-process.dynamic
  (:require [clojure.pprint :as pp]
            [quil.core :as q]))

(defn- save-file-name []
  (pp/cl-format nil
                "~d-~2,'0d-~2,'0d-~2,'0d-~2,'0d-~2,'0d.tif"
                (q/year) (q/month) (q/day) (q/hour) (q/minute) (q/seconds)))

(defn setup []
  (q/color-mode :hsb 360 100 100 1.0))

(defn draw []
  (q/no-loop)
  (q/no-fill)
  (q/background 44 10 99)
  (q/stroke 68 100 60)
  (dotimes [n 88]
    (let [x (* 10 (inc n))
          y x]
      (q/triangle x y x (+ y 10) (+ x 10) y)))
  (q/save (save-file-name)))
