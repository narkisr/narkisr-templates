(ns leiningen.new.narkisr
  (:require [leiningen.new.templates :refer [renderer name-to-path ->files]]
            [leiningen.core.main :as main]))

(def render (renderer "narkisr"))

(defn narkisr
  "Generating a base project with common libraries/plugins including:
  * timbre for logging.
  * midje and lein-midje for testing.
  * Lein set-version and lein-tag plugins for release managment. "
  [name & opts]
  (let [data {:name name :sanitized (name-to-path name) :user "USER"}
        opts-m (into {} (map (fn [[k v]] [(keyword k) v]) (partition 2 opts)))
        combined (merge data opts-m)]
    (main/info "Generating" combined)
    (->files combined
       ["project.clj" (render "project.clj" combined)]
       [".gitignore" (render "gitignore" combined)]
       ["LICENSE-2.0.txt" (render "LICENSE-2.0.txt" combined)]
       ["README.md" (render "README.md" combined)]
       [".travis.ci" (render "travis.ci" combined)]
       ["src/{{sanitized}}/core.clj" (render "core.clj" combined)])))
