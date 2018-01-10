(defproject clojure-rest "0.1.0-SNAPSHOT"
  :description "REST service as a dummy stellar-coordinator"
  :url "http://github.com/data61/stellar"
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [compojure "1.6.0"]
                 [ring/ring-defaults "0.3.1"]
                 [ring/ring-json "0.4.0"]
                 [ring/ring-jetty-adapter "1.6.2"]]
  :plugins [[lein-ring "0.12.1"]]
  :ring {:handler clojure-rest.handler/rest-if}
  :profiles
  {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                        [ring/ring-mock "0.3.0"]]}})
