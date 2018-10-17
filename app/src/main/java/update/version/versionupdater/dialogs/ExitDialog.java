package update.version.versionupdater.dialogs;

import android.content.Context;

import com.yarolegovich.lovelydialog.LovelyInfoDialog;
import com.yarolegovich.lovelydialog.LovelyStandardDialog;

import androidx.annotation.StyleRes;
import update.version.versionupdater.R;

public class ExitDialog extends LovelyStandardDialog {

    private OnExitListener listener;

    public void setListener(OnExitListener listener) {
        this.listener = listener;
    }

    public ExitDialog(Context context, @StyleRes int themeId) {
        super(context);
        init();


    }

    private void init() {
        setTopColorRes(R.color.colorAccent);
        setIcon(R.drawable.ic_upgrade);
        setTitle("Confirm action");
        setMessage("You have not saved\nAre you sure you want to exit?");
        setPositiveButton("Yes", v -> {
            if (listener != null) {
                listener.onExit();
            }
        });

        setNegativeButton("No", v -> {
            dismiss();
        });
        setCancelable(false);
    }

    public ExitDialog(Context context) {
        super(context);
        init();
    }

    public static void showDialog(Context context, OnExitListener listener){
        ExitDialog dialog = new ExitDialog(context);
        dialog.setListener(listener);
        dialog.show();
    }


    public interface OnExitListener{
        void onExit();
    }
}
