package update.version.versionupdater.base;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.MenuRes;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import update.version.versionupdater.R;

public abstract class BaseFragment<T extends  BasePresenter> extends Fragment {

    private T presenter;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        presenter = initPresenter();

    }

    protected abstract T initPresenter();

    public T getPresenter() {
        return presenter;
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(getMenuRes(), menu);
    }

    @MenuRes
    protected abstract int getMenuRes();

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();


        if (id == R.id.action_update || id == R.id.action_done) {
            handleMenuClick();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    protected abstract void handleMenuClick();

    @Override
    public void onDetach() {
        super.onDetach();
        getPresenter().stop();
    }
}
