(defproject fierce/timbre-lambda "0.0.1"
  :author "Stuart King <https://fierce.ventures>"
  :description "timbre appender for AWS Lambda"
  :license {:name         "Eclipse Public License"
            :url          "http://www.eclipse.org/legal/epl-v10.html"
            :distribution :repo
            :comments     "Same as Clojure"}
  :url "https://github.com/fierceventures/timbre-lambda"
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [com.amazonaws/aws-lambda-java-core "1.1.0"]
                 [com.taoensso/timbre "4.10.0"]]
  :source-paths ["src/main/clj"]
  :test-paths ["src/test/clj"]
  :profiles {:uberjar {:aot :all}})
