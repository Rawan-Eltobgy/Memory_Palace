package com.example.eltobgy.memorycardgame.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

import com.example.eltobgy.memorycardgame.R;

/**
 * Created by Eltobgy on 11-Aug-18.
 */




public class LevelClearedDialog extends DialogFragment {
    public interface OnCompleteListener {
        void onComplete(Bundle callbackData);
    }

    public LevelClearedDialog() {

    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {
       final OnCompleteListener dialogCompleteListenerSettings = (OnCompleteListener) getActivity();

        final AlertDialog.Builder dialogWin = new AlertDialog.Builder(getActivity());

        String time = getArguments().getString("time");


        dialogWin.setTitle(R.string.level_completed);
        dialogWin.setMessage(getString(R.string.time) + time);
        dialogWin.setPositiveButton(R.string.next_level, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
              //TODO
                Bundle callbackData = new Bundle();
                callbackData.putString("dialog", "nextlevel");
                dialogCompleteListenerSettings.onComplete(callbackData);
            }
        });
        dialogWin.setNegativeButton("mainmenu", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Bundle callbackData = new Bundle();
               // Intent myIntent = new Intent(GameScreenActivity.this, TestingScreen.class);
                callbackData.putString("dialog", "mainMenu");
                dialogCompleteListenerSettings.onComplete(callbackData);
            }
        });

        return dialogWin.create();
    }
}