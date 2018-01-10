(ns clojure-rest.handler-test
  (:require [clojure.test :refer :all]
            [ring.mock.request :as mock]
            [clojure-rest.handler :refer :all]))

(deftest test-app
  (testing "main route"
    (let [response (rest-if (mock/request :get "/"))]
      (is (= (:status response) 200))
      (is (= (:body response) "Hello World"))))

  (testing "not-found route"
    (let [response (rest-if (mock/request :get "/invalid"))]
      (is (= (:status response) 404)))))
