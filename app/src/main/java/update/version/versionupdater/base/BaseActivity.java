package update.version.versionupdater.base;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.WindowManager;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import update.version.versionupdater.utils.ActivityUtils;


public abstract class BaseActivity<T extends Fragment> extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        Fragment fragment = getSupportFragmentManager()
                .findFragmentById(android.R.id.content);

        if (fragment == null) {
            fragment = getFragment();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), fragment);
        }
    }


    public abstract T getFragment();


}
