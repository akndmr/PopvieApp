package io.github.akndmr.popvieapp.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by AKIN on 25.02.2018.
 */

public class NetworkUtil {
    //Checks if there is any connection either WiFi or Mobile
    //Reference: https://developer.android.com/training/basics/network-ops/managing.html
    public static boolean isConnected(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnectedOrConnecting());
    }
}
