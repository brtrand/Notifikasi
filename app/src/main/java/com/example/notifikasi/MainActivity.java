package com.example.notifikasi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.nio.channels.Channel;

public class MainActivity extends AppCompatActivity {
    Button bt1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createNotificationChannel();
        bt1=(Button) findViewById(R.id.bt1);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendNotification();
            }
        });
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "mychannel";
            String description = "testchannel";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("mychannel", name, importance);
            channel.setDescription(description);
            // Register the channel with the system
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
        }


    private void sendNotification() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,"mychannel")
                .setContentTitle("Ini contoh Notifikasi")
                .setContentText("Ini isi dari notifikasi")
                .setSmallIcon(R.drawable.ic_launcher_foreground);
        Intent notif = new Intent(this, MainActivity.class);
        PendingIntent content = PendingIntent.getActivity(this, 0, notif,PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(content);

        NotificationManager manager =(NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        manager.notify(0,builder.build());
    }
}