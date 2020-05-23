(ns lakepend.db
  (:require [clojure.java.jdbc :as jdbc]
            [clojure.string :as str]))

(def db-conf {:classname "org.sqlite.JDBC"
              :subprotocol "sqlite"
              :subname "resources/database.db"})

(defn insert-weather-data
  [weather-data]
  (jdbc/insert-multi! db-conf :weather weather-data))

(defn find-last-datetime
  []
  (-> (jdbc/query db-conf "select recorded_at from weather order by recorded_at desc limit 1")
      first
      :recorded_at))

(def avg-median-query
  "select avg(wind_speed) as avg_wind_speed,
  avg(air_temp) as avg_air_temp,
  avg(baro) as avg_baro,
  median(wind_speed) as med_wind_speed,
  median(air_temp) as med_air_temp,
  median(baro) as med_baro
  from weather where recorded_at >= ? AND recorded_at <= ?")

(defn find-data-in-range
  [start-ms end-ms]
  (first
    (jdbc/query db-conf [avg-median-query start-ms end-ms])))