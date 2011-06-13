(ns predators-and-prey.simulation)

(def predator {:vx 2 :vy 2 :max-velocity 7 :radius 10})
(def prey {:vx 2 :vy 2 :max-velocity 4 :radius 5})

(def animals (atom {}))

(defn create-predator [x y horizontal-velocity vertical-velocity]
	(conj {:x x :y y :vx horizontal-velocity :vy vertical-velocity} predator))
	
(defn create-prey
	([screen-size]
	(conj {:x (rand-int screen-size) :y (rand-int screen-size) :vx (rand-int (:max-velocity prey)) :vy (rand-int (:max-velocity prey))} prey))
	([x y vx vy]
	(conj {:x x :y y :vx vx :vy vy} prey)))
	
(defn prey-generator [screen-size]
	#(conj {:x (rand-int screen-size) :y (rand-int screen-size) ::vx (rand-int (:max-velocity prey)) :vy (rand-int (:max-velocity prey))} prey))
	
(defn initial-state [screen-size]
	{:predators [(create-predator 50 50 4 4) (create-predator (- screen-size 100) (- screen-size 100) -4 -4)]
	:prey (take 50 (repeatedly (prey-generator screen-size)))})

(defn think [screen-size]
	@animals)

(defn pulse [screen-size]
	(let [bounded-screen-size (- screen-size 20)]
	(if (empty? @animals)
		(swap! animals conj (initial-state screen-size)
		(swap! animals conj (think screen-size)))))
	@animals)