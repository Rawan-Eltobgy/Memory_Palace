package com.example.eltobgy.memorycardgame.utlis;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.util.Arrays;

/**
 * Created by Eltobgy on 15-Jul-18.
 */

public class Helper {
    public static void showLog(String tag,String msg){
        Log.e(tag,msg);
    }
    public static void showToast(Context context, String message){
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }
    public static int[] addElementToArray(int[] org, int added) {
        int[] result = Arrays.copyOf(org, org.length +1);
        result[org.length] = added;
        return result;
    }
}
