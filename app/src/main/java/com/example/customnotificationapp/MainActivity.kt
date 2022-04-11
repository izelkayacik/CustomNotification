package com.example.customnotificationapp

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var CHANNEL_ID = "Your_Channel_ID";

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Now Lets call our function
        CreateNotificationChannel()

        val notificationLayout = RemoteViews(packageName, R.layout.custom_notif)
        var builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("Your Title")
            .setSmallIcon(R.drawable.ic_baseline_camera_alt_24)
            .setStyle(NotificationCompat.DecoratedCustomViewStyle())
            .setCustomContentView(notificationLayout)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        notify_btn.setOnClickListener {
            with(NotificationManagerCompat.from(this)){
                notify(0, builder.build())
            }
        }
    }

    //Now Let's create a function that will allow us to create a Channel for Our notif
    private fun CreateNotificationChannel(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) // we will check if the SDK is higher than 26
        {
            val name = "App Notification"
            val descriptionText = "This is your notification description"
            val importnace : Int = NotificationManager.IMPORTANCE_DEFAULT
            val channel : NotificationChannel = NotificationChannel(CHANNEL_ID, name, importnace).apply {
                description = descriptionText
            }
            val notificationManager: NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}