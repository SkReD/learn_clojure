(use 'ring.adapter.jetty)
(use 'compojure.route)
(run-jetty (resources "/") {:port 3000 :join? false})