package update.version.versionupdater.add;

import androidx.fragment.app.Fragment;
import update.version.versionupdater.R;
import update.version.versionupdater.base.BaseActivity;
import update.version.versionupdater.utils.ActivityUtils;

public class AddActivity extends BaseActivity {
    @Override
    public Fragment getFragment() {
        int oldVersion = getIntent().getIntExtra(ActivityUtils.EXTRA_PARAMS, 1);
        initToolbar();
        return AddFragment.newInstance(oldVersion);
    }



    private void  initToolbar(){
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(getString(R.string.update_version));
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);
//            getSupportActionBar().setBackgroundDrawable(ContextCompat.getDrawable(this, R.color.colorSplash));
        }
    }
}
