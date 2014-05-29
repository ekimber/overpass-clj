(ns overpass.requests
  (:require [clj-http.client :as client]
            [overpass.utils :as utils]))

(defn boundaries-id
  "Returns a list of relations id for administrative boundaries by querying the 
  overpass endpoint. Restrict the relations to those having an admin_level of lvl. 
  When not specified, lvl defaults to 2 (country level admin boundaries)."
 [endpoint lvl]
  (let [q {:data (format "relation[\"boundary\"=\"administrative\"][\"admin_level\"=\"%s\"];out ids;" lvl)}]
    (utils/boundary-ids-extract (:body (client/get endpoint {:query-params q})))))

(defn boundary-id-xml
  "Returns the xml associated with relationid."
  [id endpoint]
   (let [q {:data (format "relation(%s);(._;>;);out body;" id)}]
     (:body (client/get endpoint {:query-params q}))))

