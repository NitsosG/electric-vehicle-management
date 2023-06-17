package aueb.msc.notification;

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import aueb.msc.R
import java.math.BigDecimal

// Create a unique channel ID for your notifications (required for Android 8.0 and above)
private const val CHANNEL_ID = "electric_vehicle_management_channel"

// Notification ID (can be any unique integer value)
private const val NOTIFICATION_ID = 1

// Notification title and content text
private const val NOTIFICATION_TITLE = "Battery Level Notification"

// Create a notification
fun batteryLevelNotification(context: Context, batteryLevel : BigDecimal) {
    // Create a notification manager
    val notificationManager =
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    // Create a notification channel (required for Android 8.0 and above)
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val channel =
            NotificationChannel(CHANNEL_ID, "My Channel", NotificationManager.IMPORTANCE_DEFAULT)
        notificationManager.createNotificationChannel(channel)
    }

    // Create a notification builder
    val builder = NotificationCompat.Builder(context, CHANNEL_ID)
        .setSmallIcon(R.drawable.ic_notification) // Set the small icon for the notification
        .setContentTitle(NOTIFICATION_TITLE) // Set the title
        .setContentText("Your batter is low $batteryLevel") // Set the content text
        .setPriority(NotificationCompat.PRIORITY_DEFAULT) // Set the priority (optional)

    // Display the notification
    notificationManager.notify(NOTIFICATION_ID, builder.build())
}
