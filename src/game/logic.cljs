(ns game.logic
  (:require [javelin.core :refer [cell] :refer-macros [cell=]]))

(def signs {:cross 2 :zero 1 :empty 0})
(def size 3)

(defn get-mark
	[board row-index column-index]
	(deref (nth (deref (nth (deref board) row-index)) column-index)))

(defn set-mark
	[board row-index column-index mark-type]
	(reset! (nth (deref (nth (deref board) row-index)) column-index) (mark-type signs)))

(defn set-cross
	[board row-index column-index]
	(set-mark board row-index column-index :cross))

(defn set-zero
	[board row-index column-index]
	(set-mark board row-index column-index :zero))

(defn get-cell
	[row-index column-index]
	(:empty signs))

(defn get-row
	[size row-index]
	(map
		#(get-cell row-index %)
		(range 0 size)))

(def board 
	(cell
		(map
			#(get-row size %)
			(range 0 size))))