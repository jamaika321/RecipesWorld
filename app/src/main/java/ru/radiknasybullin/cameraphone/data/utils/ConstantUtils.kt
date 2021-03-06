package ru.radiknasybullin.cameraphone.data.utils

class ConstantUtils {
    companion object {
        //Main
        const val ICON_SIZE = 60
        const val ITEM_WEIGHT = 0.2f
        const val HOUR_OF_DAY = 12
        const val METRIC = "metric"
        const val NETWORK_CHANGE_BROADCAST = "android.net.conn.CONNECTIVITY_CHANGE"

        //Local DB
        val DEFAULT_CITIES_LIST = listOf("Yelabuga", "Naberezhnyye Chelny", "Kazan")
        const val DATABASE_VERSION = 7
        const val ROOM_DATABASE_VERSION = 1
        const val NA = "N/A"
        const val DATABASE_NAME = "cityDB"
        const val TABLE_CITYS = "citys"
        const val TABLE_DEFAULT_CITY = "defaultcity"
        const val TABLE_FORECAST = "forecast"
        const val KEY_ID = "_id"
        const val KEY_NAME = "name"
        const val KEY_NUMBER_OF_DAY = "numberOfDay"
        const val KEY_WEATHER = "weather"
        const val KEY_TEMP_MIN_MAX = "tempMinMax"
        const val KEY_ICON = "icon"
        const val KEY_TEMP = "temp"
        const val KEY_PRESSURE = "pressure"
        const val KEY_HUMIDITY = "humidity"
        const val KEY_DESCRIPTION = "description"
        const val KEY_WIND_SPEED = "windSpeed"
        const val KEY_DAYS_OF_WEEK = "daysOfWeek"
        const val KEY_DEFAULT_CITY = "defaultCity"
        const val KEY_DT = "lastUpdate"
    }
}