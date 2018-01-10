(ns clojure-rest.handler
  (:require [compojure.core :as cmpj :refer :all]
            [compojure.route :as route]
            [compojure.handler :as handle]
            [ring.util.response :refer [response status]]
            [ring.middleware.json :as jsonmw]
            [ring.adapter.jetty :as jetty]
            [ring.middleware.keyword-params :as parsmw]
            [ring.middleware.multipart-params]))

(defn do-show [req]
  (response (str req)))

(defn start-ingestion
  ""
  [{session :sessionId :as body}]
  (response (str session)))

(cmpj/defroutes rest-routes
  (GET "/" [] "Hello there!")
  (POST "/" {body :body} (do-show body))
  (GET "/do-show" {parms :params} (do-show parms))
  (cmpj/context "/ingestor" []
                (cmpj/defroutes ingestor-routes
                  (POST "/start" {body :body} (start-ingestion body))))
  (route/not-found "Not Found"))

(def rest-if
  (-> (handle/api rest-routes)
        ;;(parsmw/wrap-keyword-params h)
        (jsonmw/wrap-json-body {:keywords? true})
        (jsonmw/wrap-json-response)))

(defn -main [& args]
  (let [port (if (nil? args) 3000 (first args))]
    (jetty/run-jetty #'rest-if {:port port :join? false})))
