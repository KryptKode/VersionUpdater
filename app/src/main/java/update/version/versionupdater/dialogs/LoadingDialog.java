package update.version.versionupdater.dialogs;

import android.content.Context;



import com.yarolegovich.lovelydialog.LovelyProgressDialog;

import androidx.annotation.DrawableRes;
import androidx.annotation.StringRes;
import update.version.versionupdater.R;

public class LoadingDialog extends LovelyProgressDialog {
    public LoadingDialog(Context context, @DrawableRes int iconRes, @StringRes int titleRes) {
        super(context);
//        setIcon(iconRes);
        setTitle(titleRes);
        setTopColorRes(R.color.colorAccent);
    }


}
