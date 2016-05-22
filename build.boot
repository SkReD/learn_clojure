(set-env!
  :dependencies '[[adzerk/boot-cljs "1.7.48-4"]
                  [adzerk/boot-cljs-repl "0.3.0"]
                  [com.cemerick/piggieback "0.2.1"]
                  [weasel "0.7.0"]
                  [org.clojure/tools.nrepl "0.2.12"]
                  [adzerk/boot-reload "0.4.7"]
                  [compojure "1.5.0"]
                  [hoplon/boot-hoplon "0.1.13"]
                  [hoplon/castra "3.0.0-alpha3"]
                  [hoplon/hoplon "6.0.0-alpha14"]
                  [hoplon/javelin "3.8.4"]
                  [org.clojure/clojure "1.8.0"]
                  [org.clojure/clojurescript "1.8.51"]
                  [pandeiro/boot-http "0.7.3"]
                  [ring "1.4.0"]
                  [ring/ring-defaults "0.2.0"]
                  [com.cemerick/piggieback "0.2.1"]
                  [weasel "0.7.0"]
                  [org.clojure/tools.nrepl "0.2.12"]]
  :source-paths #{"src"})

(require
  '[adzerk.boot-cljs :refer [cljs]]
  '[adzerk.boot-cljs-repl :refer [cljs-repl start-repl]]
  '[adzerk.boot-reload :refer [reload]]
  '[hoplon.boot-hoplon :refer [hoplon prerender]]
  '[pandeiro.boot-http :refer [serve]]
  '[service.init])

(deftask build
         []
         (comp
           (hoplon)
           ;(cljs-repl)                                      ; order is important!!
           (cljs); :optimizations :simple)
           (speak)))

(deftask server
         []
         (comp
           (watch)
           (serve
             :port 8000
             :init `service.init/jetty-init
             :reload true)))

(deftask dev
         "Build castra-chat for local development."
         []
         (comp
           (watch)
           (hoplon)
           (reload)
           (cljs-repl :port 9001)                                      ; order is important!!
           (cljs)
           (serve
             :port 8000
             ;:init `service.init/jetty-init
             :reload true)
           (speak)))

(deftask prod
         "Build castra-chat for production deployment."
         []
         (comp
           (hoplon)
           (cljs :optimizations :advanced)
           (prerender)))
