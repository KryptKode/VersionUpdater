package update.version.versionupdater.main;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;
import update.version.versionupdater.R;
import update.version.versionupdater.add.AddActivity;
import update.version.versionupdater.api.model.Version;
import update.version.versionupdater.api.model.WhatsNew;
import update.version.versionupdater.base.BaseFragment;
import update.version.versionupdater.dialogs.LoadingDialog;
import update.version.versionupdater.dialogs.MessageDialog;
import update.version.versionupdater.dialogs.NoInternetDialog;
import update.version.versionupdater.dialogs.WhatsNewDialog;
import update.version.versionupdater.utils.ActivityUtils;
import update.version.versionupdater.utils.NetworkUtils;
import update.version.versionupdater.utils.NotificationUtils;
import update.version.versionupdater.views.EmptyRecyclerView;
import update.version.versionupdater.views.ItemDivider;
import update.version.versionupdater.views.WhatsNewAdapter;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainFragment extends BaseFragment<MainFragmentPresenter> implements MainFragmentContract.MainView, WhatsNewAdapter.OnWhatsNewListener, MessageDialog.OnMessageDialogListener, WhatsNewDialog.OnMessageDialogListener {
    @BindView(R.id.tv_current_version)
    TextView currentVersionTextView;

    @BindView(R.id.tv_msg)
    TextView messageTextView;

    @BindView(R.id.rv_whats_new)
    EmptyRecyclerView recyclerView;

    @BindView(R.id.root_srl)
    SwipeRefreshLayout swipeRefreshLayout;


    @BindView(R.id.empty_view)
    View emptyView;


    @BindView(R.id.img_msg_edit)
    ImageView editImageView;


    @BindViews({R.id.tv_current_version_label, R.id.tv_msg_label})
    List<TextView> textViewList;


    private WhatsNewAdapter whatsNewAdapter;
    private Version version;
    private LoadingDialog loadingDialog;


    public MainFragment() {
    }

    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        return fragment;
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);
        initView();
        getPresenter().start();
        return view;
    }

    private void initView() {
        for (TextView textView : textViewList) {
            textView.setSelected(true);
        }
        loadingDialog = new LoadingDialog(getContext(), R.drawable.ic_reset, R.string.loading_);
        whatsNewAdapter = new WhatsNewAdapter(this);
        recyclerView.setEmptyView(emptyView);
        recyclerView.setAdapter(whatsNewAdapter);
        swipeRefreshLayout.setOnRefreshListener(() -> {
            getPresenter().start();
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new ItemDivider(getContext()));
    }


    @Override
    protected MainFragmentPresenter initPresenter() {
        return new MainFragmentPresenter(this);
    }

    @Override
    protected int getMenuRes() {
        return R.menu.menu_main;
    }

    @Override
    protected void handleMenuClick(int itemId) {
        if(itemId == R.id.action_update){
            getPresenter().handleAddClick();
        }
    }

    @Override
    public void showVersion(Version version) {
        this.version = version;
        currentVersionTextView.setText(String.valueOf(version.getVersion()));
        if (!TextUtils.isEmpty(version.getMessage())) {
            editImageView.setVisibility(View.VISIBLE);
            messageTextView.setText(version.getMessage());
        }else{
            editImageView.setVisibility(View.GONE);
        }
    }



    @Override
    public void showWhatsNew(List<WhatsNew> whatsNewList) {
        whatsNewAdapter.setWhatsNewList(whatsNewList);
    }

    @Override
    public void onMessageEdit(Version version) {
        MessageDialog.showDialog(getFragmentManager(), version, this);
    }

    @Override
    public void onWhatsNewEdit(WhatsNew whatsNew) {
        WhatsNewDialog.showDialog(getFragmentManager(), whatsNew, this);
    }

    @Override
    public void showAddInterface() {
        if (getActivity() != null) {
            ActivityUtils.invokeActivity(getActivity(), AddActivity.class, false);
        }
    }

    @Override
    public void notifyUser(int string) {
        if (getContext() != null) {
            NotificationUtils.notifyUser(getContext(), getString(string));
        }
    }

    @Override
    public boolean isOffline() {
        if (getContext() != null) {
            return !NetworkUtils.isOnline(getContext());
        }
        return false;
    }

    @Override
    public void showProgress() {
        swipeRefreshLayout.setRefreshing(true);
        loadingDialog.show();
    }

    @Override
    public void hideProgress() {
        swipeRefreshLayout.setRefreshing(false);
        loadingDialog.dismiss();
    }

    @Override
    public void showNoInternetMessage() {
        if (getContext() != null) {
            NoInternetDialog.showDialog(getContext());
        }
    }

    @Override
    public void onClick(WhatsNew whatsNew) {
        getPresenter().handleWhatsNewClick(whatsNew);
    }

    @OnClick(R.id.img_msg_edit)
    public void editMessage(){
        getPresenter().handleMessageEditClick(version);
    }

    @Override
    public void onMessageListener(Version version, boolean isEdit) {
        getPresenter().updateMessage(version);
    }

    @Override
    public void onWhatsNewListener(WhatsNew whatsNew, boolean isEdit) {
        getPresenter().updateWhatsNew(whatsNew);
    }
}
