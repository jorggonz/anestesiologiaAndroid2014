package mc.mCongressDemo;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * Created by luisgonzalez on 29-08-14.
 */
public class ServicioAlarma extends Service {
    private NotificationManager mManager;

    @Override
    public IBinder onBind(Intent arg0)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void onCreate()
    {
        // TODO Auto-generated method stub
        super.onCreate();
    }

    @SuppressWarnings("static-access")
    @Override
    public void onStart(Intent intent, int startId)
    {
        super.onStart(intent, startId);


        mManager = (NotificationManager) this.getApplicationContext().getSystemService(this.getApplicationContext().NOTIFICATION_SERVICE);
        Intent intent1 = new Intent(this.getApplicationContext(),MainActivity.class);
        intent1.putExtra("fav_view",true);
        Notification notification = new Notification(R.drawable.ic_launcher,"Un evento favorito esta por empezar", System.currentTimeMillis());
      //  intent1.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP| Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
        intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent pendingNotificationIntent = PendingIntent.getActivity(this.getApplicationContext(), 0, intent1, PendingIntent.FLAG_UPDATE_CURRENT);
        notification.flags |= Notification.FLAG_AUTO_CANCEL;

        notification.setLatestEventInfo(this.getApplicationContext(), myApp.getContext().getString(R.string.app_name), intent.getExtras().getString("mensaje"), pendingNotificationIntent);

        mManager.notify(0, notification);

    }

    @Override
    public void onDestroy()
    {
        // TODO Auto-generated method stub
        super.onDestroy();
    }
}
