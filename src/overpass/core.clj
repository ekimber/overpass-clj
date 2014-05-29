(ns overpass.core
  (:gen-class)
  (:require [overpass.operations :as operations]
            [clojure.tools.cli :refer [parse-opts]]))

(def overpass-endpoint-default "http://overpass-api.de/api/interpreter")
(def directory-default "./data")
(def admin-level-default "2")
(def cli-opts
  [["-e" "--endpoint ENDPOINT" (format "url of the overpass api to use. Defaults to %s." overpass-endpoint-default)
    :default overpass-endpoint-default]
   ["-d" "--directory DIRRECTORY" (format "directory in which we want to store the result files. Defaults to %s." directory-default)
    :default directory-default]
   ["-l" "--level ADMINLEVEL" (format "admin level of the boundaries to select. Defaults to %s" admin-level-default)
    :default admin-level-default]
   ["-h" "--help"]])

(defn -main
  [& args]
  (let [{:keys [options arguments errors summary]} (parse-opts args cli-opts)]
    (when errors
      (doseq [e errors]
        (println e))
      (println "please see usage summary below.")
      (println summary)
      (System/exit -1))
    (when (:help options)
      (println "usage summary")
      (println summary)
      (System/exit 0))
    (let [{:keys [endpoint directory level]} options]
      (operations/boundary-files! endpoint directory level))))
