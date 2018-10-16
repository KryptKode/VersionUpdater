package update.version.versionupdater.add;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import update.version.versionupdater.R;
import update.version.versionupdater.base.BaseFragment;
import update.version.versionupdater.base.BasePresenter;

public class AddFragment extends BaseFragment {

    public static AddFragment newInstance() {
        
        Bundle args = new Bundle();
        
        AddFragment fragment = new AddFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add, container, false);
    }


    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected int getMenuRes() {
        return R.menu.menu_add;
    }

    @Override
    protected void handleMenuClick() {

    }
}
