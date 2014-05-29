(ns overpass.operations
  (:require [overpass.utils :as utils]
            [overpass.requests :as requests]
            [clojure.core.reducers :as r]
            [clj-progress.core :as p]))

(defn boundary-files!
  "saves all the lvl administrative boundary to osm files. File name is the relation id."
  [endpoint dir lvl]
    (let [ids (requests/boundaries-id endpoint lvl)]
      (p/init (format "saving admin_level %s files in %s fetched from %s" lvl dir endpoint) (count ids))

      (doseq [id ids]
        (-> id
            (requests/boundary-id-xml endpoint)
            (utils/save-osm! (utils/join-path dir id)))
        (p/tick))
      (p/done)))
