(ns timbre-lambda.logging
  (:require [clojure.string :as str]
            [taoensso.timbre :as timbre])
  (:import (com.amazonaws.services.lambda.runtime LambdaRuntime)))

(defn- output
  [request-id {:keys [level ?err msg_ ?ns-str ?file timestamp_ ?line]}]
  (str
    (force timestamp_) " "
    (str "<" request-id "> ")
    (str/upper-case (name level)) " "
    "[" (or ?ns-str ?file "?") ":" (or ?line "?") "] - "
    (force msg_)
    (when-let [err ?err]
      (str "\n" (timbre/stacktrace err)))))

(def ^:private logger (LambdaRuntime/getLogger))

(defn- log
  [{:keys [output_]}]
  (.log logger (force output_)))

(defn- appender
  "Configure an appender for this request"
  [context]
  {:enabled?       true
   :async?         true
   :min-level      :info
   :output-fn      (partial output (.getAwsRequestId context))
   :timestamp-opts {:pattern  "yyyy-MM-dd HH:mm:ss"
                    :locale   :jvm-default
                    :timezone :utc}
   :fn             log})

(defn configure-logging
  [context]
  (taoensso.timbre/merge-config! {:appenders {:lambda  (appender context)
                                              :println {:enabled? false}}}))
