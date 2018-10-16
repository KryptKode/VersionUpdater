package update.version.versionupdater.main;

import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import update.version.versionupdater.R;
import update.version.versionupdater.base.BaseActivity;

public class MainActivity extends BaseActivity<MainFragment> {

    @Override
    public MainFragment getFragment() {
        return MainFragment.newInstance();
    }

}
