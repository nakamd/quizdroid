package nakamd.washington.edu.quizdroid;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by danielnakamura on 2/25/15.
 */
public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String url = intent.getStringExtra("url for future");
        Toast.makeText(context, "Downloading New Questions from " + url, Toast.LENGTH_SHORT).show();
    }

}
