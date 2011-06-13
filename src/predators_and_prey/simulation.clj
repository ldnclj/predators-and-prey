(ns predators-and-prey.simulation)

(def predator {:velocity 2 :max-velocity 7 :radius 10})
(def prey {:velocity 2 :max-velocity 4 :radius 5})

(defn create-predator [x y heading]
	(conj {:x x :y y :heading heading} predator))

(defn pulse []
	{:predators [(create-predator 50 50 200) (create-predator 80 50 200)]})