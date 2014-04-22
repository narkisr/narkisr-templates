(ns leiningen.new.narkisr
  (:require [leiningen.new.templates :refer [renderer name-to-path ->files]]
            [leiningen.core.main :as main]))

(def render (renderer "narkisr"))

(defn narkisr
  "Generating a base project with common libraries/plugins including:
  * timbre for logging.
  * midje and lein-midje for testing.
  * Lein set-version and lein-tag plugins for release managment. "
  [name]
  (let [data {:name name
              :sanitized (name-to-path name)}]
    (main/info "Generating fresh 'lein new' base project.")
    (->files data
       ["project.clj" (render "project.clj" data)]
       ["src/{{sanitized}}/core.clj" (render "core.clj" data)])))
