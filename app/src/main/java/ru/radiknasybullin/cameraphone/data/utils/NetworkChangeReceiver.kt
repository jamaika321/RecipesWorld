package ru.radiknasybullin.cameraphone.data.utils

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.NetworkInfo
import ru.radiknasybullin.cameraphone.domain.interfaces.CommonInterfaces

class NetworkChangeReceiver(val view: CommonInterfaces.BroadCastReceiver) : BroadcastReceiver() {
    private val intentFilter = IntentFilter(ConstantUtils.NETWORK_CHANGE_BROADCAST)

    override fun onReceive(context: Context?, intent: Intent?) {

        val action = intent?.action

        when (action){
            ConstantUtils.NETWORK_CHANGE_BROADCAST -> {
                val networkInfo = intent.getParcelableExtra<NetworkInfo>(ConnectivityManager.EXTRA_NETWORK_INFO)
                if(networkInfo != null && networkInfo.detailedState == NetworkInfo.DetailedState.CONNECTED){
                    view.onInternetConnectionSuccess()
                } else {
                    view.onInternetConnectionError()
                }

                /*val mConnectivityManager = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
                val wifi = mConnectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
                val mobile = mConnectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)

                if (wifi.isAvailable || mobile.isAvailable) {
                    view.onInternetConnectionSuccess()
                }else{
                    view.onInternetConnectionError()
                }*/
            }
        }
    }

    fun intentFilter(): IntentFilter {
        return intentFilter
    }
}