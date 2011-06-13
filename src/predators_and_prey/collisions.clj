(ns predators-and-prey.collisions)

(defn collides? [ball1 ball2]
  (let [dx (- (:x ball1) (:x ball2))
	dy (- (:y ball1) (:y ball2))]
    (< (Math/sqrt (+ (* dx dx) (* dy dy)))
       (/ (+ (:radius ball1) (:radius ball2)) 2))))
