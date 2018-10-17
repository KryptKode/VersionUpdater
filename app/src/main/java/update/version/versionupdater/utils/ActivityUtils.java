package update.version.versionupdater.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import static androidx.core.util.Preconditions.checkNotNull;



/**
 * This provides methods to help Activities load their UI.
 */
public class ActivityUtils {
    public static final String EXTRA_PARAMS = "extra_params";
    /**
     * The {@code fragment} is added to the container view with id {@code frameId}. The operation is
     * performed by the {@code fragmentManager}.
     *
     */


    public static void invokeActivity(Activity activity, Class<?> tClass, boolean shouldFinish) {
        Intent intent = new Intent(activity, tClass);
        activity.startActivity(intent);
        if (shouldFinish) {
            activity.finish();
        }
    }


    public static void invokeActivityWithIntExtra(Activity activity, Class<?> tClass, int extra) {
        Intent intent = new Intent(activity, tClass);
        intent.putExtra(EXTRA_PARAMS, extra);
        activity.startActivity(intent);
    }



    @SuppressLint("RestrictedApi")
    public static void addFragmentToActivity(FragmentManager fragmentManager, Fragment fragment){
        checkNotNull(fragmentManager);
        checkNotNull(fragment);
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        transaction.replace(android.R.id.content, fragment);
        transaction.commit();
    }

}
