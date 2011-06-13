(ns predators-and-prey.simulation)

(def predator {:velocity 2 :max-velocity 7 :radius 10})
(def prey {:velocity 2 :max-velocity 4 :radius 5})

(def animals (atom {}))

(defn create-predator [x y heading]
	(conj {:x x :y y :heading heading} predator))
(defn create-prey
	([screen-size]
	(conj {:x (rand-int screen-size) :y (rand-int screen-size) :heading (rand-int 360)} prey))
	([x y heading]
	(conj {:x x :y y :heading heading} prey)))
	
(defn prey-generator [screen-size]
	#(conj {:x (rand-int screen-size) :y (rand-int screen-size) :heading (rand-int 360)} prey))
	
(defn initial-state [screen-size]
	{:predators [(create-predator 50 50 200) (create-predator (- screen-size 100) (- screen-size 100) 200)]
	:prey (take 50 (repeatedly (prey-generator screen-size)))})

(defn think [screen-size]
	@animals)

(defn pulse [screen-size]
	(let [bounded-screen-size (- screen-size 20)]
	(if (empty? @animals)
		(swap! animals conj (initial-state screen-size)
		(swap! animals conj (think screen-size)))))
	@animals)