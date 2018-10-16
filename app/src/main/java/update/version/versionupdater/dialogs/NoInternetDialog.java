package update.version.versionupdater.dialogs;

import android.content.Context;

import com.yarolegovich.lovelydialog.LovelyInfoDialog;

import androidx.annotation.StyleRes;
import update.version.versionupdater.R;

public class NoInternetDialog extends LovelyInfoDialog {
    public NoInternetDialog(Context context, @StyleRes int themeId) {
        super(context);
        init();


    }

    private void init() {
        setTopColorRes(R.color.colorAccent);
        setIcon(R.drawable.ic_cloud_off);
        setTitle("Error");
        setMessage("No internet connection\nPlease turn it on");
        setConfirmButtonText("Dismiss");
        setCancelable(true);
    }

    public NoInternetDialog(Context context) {
        super(context);
        init();
    }

    public static void showDialog(Context context){
        NoInternetDialog dialog = new NoInternetDialog(context);
        dialog.show();
    }
}
