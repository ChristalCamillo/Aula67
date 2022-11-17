package br.com.zup.cloudmessagetestapp.domain

import android.content.Intent
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseCloudService : FirebaseMessagingService() {

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        val broadcaster = LocalBroadcastManager.getInstance(baseContext)
        val intent = Intent(CURRENT_TOKEN)
        intent.putExtra(TOKEN_KEY, token)
        broadcaster.sendBroadcast(intent)

    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        message.notification?.let{
            val broadcaster = LocalBroadcastManager.getInstance(baseContext)
            val intent = Intent(NEW_NOTIFICATION)
            intent.putExtra(NOTIFICATION_TITLE_KEY, it.title)
            intent.putExtra(NOTIFICATION_BODY_KEY, it.body)
            broadcaster.sendBroadcast(intent)
        }
    }



    companion object {
        const val NEW_NOTIFICATION = "NEW_NOTIFICATION"
        const val CURRENT_TOKEN = "CURRENT_TOKEN"
        const val TOKEN_KEY = "TOKEN_KEY"
        const val NOTIFICATION_TITLE_KEY = "NOTIFICATION_TITLE_KEY"
        const val NOTIFICATION_BODY_KEY = "NOTIFICATION_BODY_KEY"
    }
}