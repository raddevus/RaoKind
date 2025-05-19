package com.newlibre.raokind

object AppConstants {
    const val DEV_URL = "http://192.168.5."
    const val DEV_DESKTOP = "195:7103/"
    const val DEV_BASE_URL = "${DEV_URL}${DEV_DESKTOP}"
    const val PROD_BASE_URL = "https://newlibre.com/kind/api/"
    var API_BASE_URL =  DEV_BASE_URL
}