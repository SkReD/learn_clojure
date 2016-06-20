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
                  [deraen/boot-less "0.5.0"]
                  [differ "0.3.1"]]
  :source-paths   #{"src/shared" "src/hl"}
  :resource-paths #{"resources" "src/clj"})

(require
  '[adzerk.boot-cljs :refer [cljs]]
  '[adzerk.boot-cljs-repl :refer [cljs-repl start-repl]]
  '[adzerk.boot-reload :refer [reload]]
  '[hoplon.boot-hoplon :refer [hoplon prerender]]
  '[pandeiro.boot-http :refer [serve]]
  '[deraen.boot-less :refer [less]])

(deftask build
         []
         (comp
           (hoplon)
           ;(cljs-repl)                                      ; order is important!!
           (cljs); :optimizations :simple)
           (speak)))

(deftask server-repl
         []
         (comp
           (repl :init-ns 'server.store)))

(deftask dev
         ""
         []
         (comp
           (watch)
           (cljs-repl :port 9001)           
           (less)
           (hoplon)
           (reload)
           (cljs)
           (serve
             :port 8000
             :handler 'server.handler/app
             :reload true)
           (speak)))

(deftask prod
         "Build castra-chat for production deployment."
         []
         (comp
           (hoplon)
           (cljs :optimizations :advanced)
           (prerender)))
