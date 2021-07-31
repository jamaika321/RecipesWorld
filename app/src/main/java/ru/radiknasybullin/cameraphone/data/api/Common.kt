package ru.radiknasybullin.cameraphone.data.api

object Common {
    val retrofitServices: RetrofitServices
        get() = RetrofitClient.getClient()
}