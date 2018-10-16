package update.version.versionupdater.utils;

import android.content.Context;

import android.view.View;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class NotificationUtils {
    public static void notifyUser(View view, String message){
        Snackbar.make(view, message, Snackbar.LENGTH_LONG).show();
    }

    public static void notifyUser(Context context, String message){
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}
