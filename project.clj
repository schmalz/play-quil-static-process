(defproject play-quil-static-process "0.1.0-SNAPSHOT"
  :description "Play with a process for producing static quil sketches."
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.9.0"]
                 [quil "3.0.0"]]
  :jvm-opts ["-Xms1100m" "-Xmx1100M" "-server"]
  :aot [play-quil-static-process.dynamic])
