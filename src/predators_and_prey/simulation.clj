(ns predators-and-prey.simulation
	(:use predators-and-prey.constants))

(def predator {:max-velocity 7 :radius 10})
(def prey {:max-velocity 4 :radius 5})

(def animals (atom {}))

(defn create-predator [x y horizontal-velocity vertical-velocity]
	(conj {:x x :y y :vx horizontal-velocity :vy vertical-velocity} predator))
	
(defn create-prey
	([screen-size]
	(conj {:x (rand-int screen-size) :y (rand-int screen-size) :vx (rand-int (:max-velocity prey)) :vy (rand-int (:max-velocity prey))} prey))
	([x y vx vy]
	(conj {:x x :y y :vx vx :vy vy} prey)))
	
(defn prey-generator [screen-size]
	#(conj {:x (rand-int screen-size) :y (rand-int screen-size) :vx (rand-int (:max-velocity prey)) :vy (rand-int (:max-velocity prey))} prey))
	
(defn initial-state []
	{:predators [(create-predator 50 50 4 4)
	(create-predator (- screen-size 100) (- screen-size 100) -4 -4)]
	:prey (take 50 (repeatedly (prey-generator screen-size)))})

(defn move [animal]
	(let [x (:x animal) y (:y animal)
	vx (:vx animal) vy (:vy animal)]
	(assoc animal :x (mod (+ x vx) screen-size) :y (mod (+ y vy) screen-size))))
	
(defn think [current-state]
	(let [new-predators (map move (:predators current-state))]
	( -> @animals
		(assoc :predators new-predators))))
	;; Eat the prey that have been caught))

(defn pulse []
	(let [bounded-screen-size (- screen-size 20)]
	(if (empty? @animals)
		(reset! animals (initial-state))
		(swap! animals think))))
