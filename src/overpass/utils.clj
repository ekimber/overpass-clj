(ns overpass.utils
  (:use net.cgrand.enlive-html)
  (:require [clojure.java.io :as io]))

(defn boundary-ids-extract
  "Returns a list of ids from an osm overpass response."
  [body]
  (let [resource (xml-resource (java.io.StringReader. body))
        relations (select resource [:relation])]
    (for [{{id :id} :attrs} relations] id)))

(defn join-path
  [& args]
  (let [f (apply io/file args)]
    (.getPath f)))

(defn save-osm!
  "saves body to file named fname.osm."
  [body fname]
  (with-open [wrtr (io/writer (format "%s.osm" fname))]
    (.write wrtr body)))
