package com.example.beerdelivery;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;

public class LoadDialog {


    Activity activity;

    AlertDialog alertDialog;

    public LoadDialog(Activity myActivity){
        activity = myActivity;

    }



    public void CarregarPagina(){


        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        LayoutInflater inflater = activity.getLayoutInflater();


        builder.setView(inflater.inflate(R.layout.load, null));

        builder.setCancelable(false);
        alertDialog = builder.create();
        alertDialog.show();

    }

    public void dismissDialog(){

        alertDialog.dismiss();
    }
}
