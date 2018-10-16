package update.version.versionupdater.add;

import android.os.Bundle;

import update.version.versionupdater.base.BaseFragment;

public class AddFragment extends BaseFragment {

    public static AddFragment newInstance() {
        
        Bundle args = new Bundle();
        
        AddFragment fragment = new AddFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
