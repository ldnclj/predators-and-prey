(ns predators-and-prey.vectors)

(defn ortho [[x y]]
  [(- y) x])

(defn add [[u v] [x y]]
  [(+ u x) (+ v y)])

(defn sub [[u v] [x y]]
  [(- u x) (- v y)])

(defn len [[x y]]
  (Math/sqrt (+ (* x x) (* y y))))

(defn unit [[x y]]
  [(/ x (vlen [x y]))
   (/ y (vlen [x y]))])

(defn mul [a [x y]]
  [(* a x) (* a y)])
