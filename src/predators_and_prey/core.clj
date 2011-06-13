(ns predators-and-prey.core
	(:use [rosado.processing]
		[rosado.processing.applet]
		[predators-and-prey.constants])
	(:require [predators-and-prey.simulation :as simulation]))



(defn draw-predator [predator]
	(stroke 235 0 0)
	(fill 235 0 0)
	(ellipse (:x predator) (:y predator) (:radius predator) (:radius predator)))

(defn draw-prey [prey]
	(stroke 0 235 0)
	(fill 0 235 0)
	(ellipse (:x prey) (:y prey) (:radius prey) (:radius prey))
)

(defn draw
	"Called every animation frame"
	[]
	(let [data (simulation/pulse)]
		(background 51)
	
		(doall (map draw-predator (:predators data)))
		(doall (map draw-prey (:prey data)))
	))

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