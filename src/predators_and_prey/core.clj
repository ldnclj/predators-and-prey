(ns predators-and-prey.core
	(:use [rosado.processing] [rosado.processing.applet])
	(:require [predators-and-prey.simulation :as simulation]))

(def screen-size 800)


(defn draw-predator [predator]
	(stroke 235 0 0)
	(fill 235 0 0)
	(ellipse (:x predator) (:y predator) (:radius predator) (:radius predator)))
	
(defn draw
	"Called every animation frame"
	[]
(let [data (simulation/pulse)]
	(background 51)

	(doall (map draw-predator (:predators data)))

  (fill-float (rand-int 125) (rand-int 125) (rand-int 125))
	(radians 45)
  (with-translation [(/ 200 2) (/ 200 2)]
    (with-rotation [QUARTER_PI]
      (begin-shape)
      (vertex -50  50)
      (vertex  50  50)
      (vertex  50 -100)
      (vertex -50 -50)
      (end-shape CLOSE)))))

(defn setup []
	"Runs once."
	(smooth)
	(no-stroke)
(framerate 60))

(defapplet predators :title "Predators and Prey"
:setup setup :draw draw :size [screen-size screen-size])

(defn pnp-start []
	(run predators)
)

(defn pnp-stop []
	(stop predators)
)