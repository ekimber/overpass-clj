(defproject overpass "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [clj-http "0.9.2"]
                 [enlive "1.1.5"]
                 [org.clojure/tools.cli "0.3.1"]
                 [intervox/clj-progress "0.1.1"]]
  :main ^:skip-aot overpass.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})

