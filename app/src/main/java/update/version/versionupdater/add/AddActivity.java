package update.version.versionupdater.add;

import androidx.fragment.app.Fragment;
import update.version.versionupdater.base.BaseActivity;

public class AddActivity extends BaseActivity {
    @Override
    public Fragment getFragment() {
        return AddFragment.newInstance();
    }
}
