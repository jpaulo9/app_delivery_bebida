package com.example.beerdelivery;

import android.annotation.TargetApi;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.provider.Settings;

import androidx.core.app.NotificationCompat;


public class Notificar_Pedido extends ContextWrapper {


    private static final String EDMTV_CHANNEL_ID = "com.example.beerdelivery.COMDEV";
    private static final String EDMTV_CHANNEL_NAME = "COMDEV channel";
    private NotificationManager manager;


    public Notificar_Pedido(Context base) {
        super(base);

        CreateChannels();
    }


    @TargetApi(Build.VERSION_CODES.O)
    public void CreateChannels(){


        NotificationChannel channel = new NotificationChannel
                (EDMTV_CHANNEL_ID, EDMTV_CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH);

        channel.enableLights(true);
        channel.enableVibration(true);
        channel.setLightColor(Color.GREEN);
        channel.setLockscreenVisibility(android.app.Notification.VISIBILITY_PRIVATE);

        getManager().createNotificationChannel(channel);


    }

     public NotificationManager getManager(){
        if (manager == null) {
            manager = (NotificationManager) getSystemService(Notificar_Pedido.NOTIFICATION_SERVICE);
        }

        return manager;
     }

     @TargetApi(Build.VERSION_CODES.O)
     public NotificationCompat.Builder builder (String usuario, String msg){


         Intent intent = new Intent(this, Login.class);
         PendingIntent pdIntent = PendingIntent.getActivity
                 (this, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        return new NotificationCompat.Builder(getApplicationContext(), EDMTV_CHANNEL_ID)
                .setContentTitle(usuario)
                .setContentText(msg)
                .setSmallIcon(R.drawable.ic_launcher_1_foreground)

                .setSound(Settings.System.DEFAULT_NOTIFICATION_URI)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setAutoCancel(true).setContentIntent(pdIntent);
     }

}
