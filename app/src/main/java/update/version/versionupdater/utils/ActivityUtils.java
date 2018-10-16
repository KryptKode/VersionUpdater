package update.version.versionupdater.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import static androidx.core.util.Preconditions.checkNotNull;



/**
 * This provides methods to help Activities load their UI.
 */
public class ActivityUtils {

    /**
     * The {@code fragment} is added to the container view with id {@code frameId}. The operation is
     * performed by the {@code fragmentManager}.
     *
     */
    @SuppressLint("RestrictedApi")
    public static void addFragmentToActivity (@NonNull FragmentManager fragmentManager,
                                              @NonNull Fragment fragment, int frameId) {
        checkNotNull(fragmentManager);
        checkNotNull(fragment);
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(frameId, fragment);
        transaction.commit();
    }

    public static void invokeActivity(Activity activity, Class<?> tClass, boolean shouldFinish) {
        Intent intent = new Intent(activity, tClass);
        activity.startActivity(intent);
        if (shouldFinish) {
            activity.finish();
        }
    }

   /* public static void invokeActivityWithParcelableExtra(Activity activity, Class<?> tClass, Parcelable parcelable, boolean shouldFinish) {
        Intent intent = new Intent(activity, tClass);
        intent.putExtra(Constants.EXTRA_PARAMS, parcelable);
        activity.startActivity(intent);
        if (shouldFinish) {
            activity.finish();
        }
    }


    public static void invokeActivityWithString(Activity activity, Class<?> tClass, String uid, String username, boolean shouldFinish) {
        Intent intent = new Intent(activity, tClass);
        intent.putExtra(Constants.EXTRA_PARAMS, uid);
        intent.putExtra(Constants.ARG_RECEIVER, username);
        activity.startActivity(intent);
        if (shouldFinish) {
            activity.finish();
        }
    }


    public static void showFragment(FragmentManager fragmentManager, Fragment fragment){
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        transaction.replace(R.id.fragment_root, fragment);
        transaction.commit();
    }

    public static void showNoLayoutFragment(FragmentManager fragmentManager, Fragment fragment){
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        transaction.replace(android.R.id.content, fragment);
        transaction.commit();
    }*/

}
