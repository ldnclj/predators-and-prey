(ns predators-and-prey.core
	(:use [rosado.processing] [rosado.processing.applet])
	(:require [predators-and-prey.simulation :as simulation]))

(def screen-size 800)

(defn draw
	"Example usage of with-translation and with-rotation."
	[]
(let [data (simulation/pulse)]
  (background-float 125)
  (stroke-float 10)
  (fill-float (rand-int 125) (rand-int 125) (rand-int 125))
  (with-translation [(/ 200 2) (/ 200 2)]
    (with-rotation [QUARTER_PI]
      (begin-shape)
      (vertex -50  50)
      (vertex  50  50)
      (vertex  50 -50)
      (vertex -50 -50)
      (end-shape CLOSE)))
  (filter-kind INVERT)))

(defn setup []
  "Runs once."
  (smooth)
  (no-stroke)
  (fill 255)
  (framerate 10))

(defapplet predators :title "Predators and Prey"
:setup setup :draw draw :size [screen-size screen-size])

(defn pnp-start []
	(run predators)
)

(defn pnp-stop []
	(stop predators)
)