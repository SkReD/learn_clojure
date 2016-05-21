(ns game.logic
  (:require [javelin.core]))

(def state (javelin.core/cell {:crosses [] :zeros []}))
(def signs {:cross 2 :zero 1 :empty 0})
(def size 3)

(defn is-checked
	[checks-list row-index column-index]
	(some
		#(and
			(= (:row-index %) row-index)
			(= (:column-index %) column-index))
		checks-list))

(defn set-mark
	[mark-type row-index column-index]
	(if-not (some #(is-checked % row-index column-index) (vals @state))
	  (swap! state update-in [mark-type] conj {:row-index row-index :column-index column-index})))

(defn set-cross
	[row-index column-index]
	(set-mark :crosses row-index column-index))

(defn set-zero
	[row-index column-index]
	(set-mark :zeros row-index column-index))

(defn get-cell
	[state row-index column-index]
	(if
		(is-checked (:crosses state) row-index column-index)
		(:cross signs)
		(if
			(is-checked (:zeros state) row-index column-index)
			(:zero signs)
			(:empty signs))))

(defn get-row
	[state size row-index]
	(map
		#(get-cell state row-index %)
		(range 0 size)))

(def board (javelin.core/cell=
	(map
		#(get-row state size %)
		(range 0 size))))