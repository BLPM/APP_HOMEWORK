package com.example.a8kot

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.widget.Toast

class MyService : Service() {
    override fun onCreate() {
        super.onCreate()
        Thread(Runnable {
            try {
                Thread.sleep(2000)

                val intent = Intent(this@MyService, Main2Activity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
               this.startActivity(intent)


            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
        }).start()
        stopSelf()
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        super.onStartCommand(intent, flags, startId)
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onBind(intent: Intent): IBinder? {
        // TODO: Return the communication channel to the service.
        throw UnsupportedOperationException("Not yet implemented")
    }
}
