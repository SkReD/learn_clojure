(defproject learn_clojure "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [
                 [org.clojure/clojure "1.7.0"]
                 [org.clojure/tools.namespace "0.2.11"]
                 [org.clojure/clojurescript "1.8.40"]
                 [compojure "1.5.0"]
                 [ring/ring-core "1.4.0"]
                 [ring/ring-jetty-adapter "1.4.0"]]
  :plugins [
            [lein-cljsbuild "1.1.3"]
            [lein-exec "0.3.6"]]
  :main ^:skip-aot cljs.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}}
  :source-paths ["src/clj"]
  :cljsbuild {
              :builds [{
                        :source-paths ["src/cljs"]
                        :compiler {
                                   :output-to "resources/public/main.js"
                                   :optimizations :advanced
                                   :pretty-print true
                                   }
                        }]
              })
