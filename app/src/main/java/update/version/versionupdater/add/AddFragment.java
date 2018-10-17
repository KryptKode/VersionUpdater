package update.version.versionupdater.add;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.core.widget.TextViewCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;
import update.version.versionupdater.R;
import update.version.versionupdater.api.model.Version;
import update.version.versionupdater.api.model.WhatsNew;
import update.version.versionupdater.base.BaseFragment;
import update.version.versionupdater.dialogs.ExitDialog;
import update.version.versionupdater.dialogs.LoadingDialog;
import update.version.versionupdater.dialogs.MessageDialog;
import update.version.versionupdater.dialogs.NoInternetDialog;
import update.version.versionupdater.dialogs.WhatsNewDialog;
import update.version.versionupdater.utils.NetworkUtils;
import update.version.versionupdater.utils.NotificationUtils;
import update.version.versionupdater.views.EmptyRecyclerView;
import update.version.versionupdater.views.ItemDivider;
import update.version.versionupdater.views.SupportVectorDrawablesButton;
import update.version.versionupdater.views.WhatsNewAdapter;

public class AddFragment extends BaseFragment<AddFragmentPresenter> implements AddFragmentContract.AddView, WhatsNewAdapter.OnWhatsNewListener, WhatsNewDialog.OnMessageDialogListener, MessageDialog.OnMessageDialogListener {

    public static final String VERSION_KEY = "version_key";

    @BindView(R.id.tv_current_version)
    TextView currentVersionTextView;


    @BindView(R.id.tv_new_version)
    TextView newVersionTextView;


    @BindView(R.id.edit_msg)
    TextInputEditText messageEditText;

    @BindView(R.id.rv_whats_new)
    EmptyRecyclerView recyclerView;


    @BindView(R.id.empty_view)
    View emptyView;

    @BindView(R.id.btn_add_msg)
    SupportVectorDrawablesButton addMessageButton;


    @BindViews({R.id.tv_current_version_label, R.id.tv_new_version_label})
    List<TextView> textViewList;




    private WhatsNewAdapter whatsNewAdapter;
    private List<WhatsNew> whatsNewList;
    private Version version;
    private LoadingDialog loadingDialog;


    private int oldVersion;

    public static AddFragment newInstance(int oldVersion) {
        
        Bundle args = new Bundle();
        args.putInt(VERSION_KEY, oldVersion);
        AddFragment fragment = new AddFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (getArguments() != null) {
            oldVersion = getArguments().getInt(VERSION_KEY);
        }

        View view = inflater.inflate(R.layout.fragment_add, container, false);

        ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView() {
        for (TextView textView : textViewList) {
            textView.setSelected(true);
        }
        loadingDialog = new LoadingDialog(getContext(), R.drawable.ic_reset, R.string.loading_);
        whatsNewAdapter = new WhatsNewAdapter(this);
        whatsNewList = new ArrayList<>();

        version = new Version();
        version.setVersion(oldVersion + 1);

        currentVersionTextView.setText(String.valueOf(oldVersion));
        newVersionTextView.setText(String.valueOf(oldVersion + 1));


        recyclerView.setEmptyView(emptyView);
        recyclerView.setAdapter(whatsNewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new ItemDivider(getContext()));
    }


    @Override
    protected AddFragmentPresenter initPresenter() {
        return new AddFragmentPresenter(this);
    }

    @Override
    protected int getMenuRes() {
        return R.menu.menu_add;
    }

    @Override
    protected void handleMenuClick(int itemId) {
        if (itemId == android.R.id.home){
            getPresenter().handleExit();
        }else if (itemId == R.id.action_done){
            getPresenter().handleSaveClick(version, whatsNewList);
        }
    }

    @Override
    public void onClick(WhatsNew whatsNew) {
        WhatsNewDialog.showDialog(getFragmentManager(), whatsNew, this);
    }


    @OnClick(R.id.btn_add_msg)
    public void onAddMessage(){
        getPresenter().handleAddMessageClick(String.valueOf(messageEditText.getText()));
    }

    @OnClick(R.id.img_add)
    public void onAdd(){
        getPresenter().handleIncrementVersionClick(Integer.parseInt(newVersionTextView.getText().toString()));
    }


    @OnClick(R.id.btn_add_whats_new)
    public void onAddWhatsNew(){
        getPresenter().handleAddWhatsNewClick(whatsNewList.size());
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
        loadingDialog.show();
    }

    @Override
    public void hideProgress() {
        loadingDialog.dismiss();
    }

    @Override
    public void showNoInternetMessage() {
        if (getContext() != null) {
            NoInternetDialog.showDialog(getContext());
        }
    }



    @Override
    public void showAddMessageDialog() {
        MessageDialog.showDialog(getFragmentManager(), version, this);
    }

    @Override
    public void showAddWhatsNewDialog() {
        WhatsNew whatsNew = new WhatsNew();
        whatsNew.setId(whatsNewList.size());
        WhatsNewDialog.showDialog(getFragmentManager(), whatsNew, this);
    }

    @Override
    public void incrementVersion(int count) {
        version.setVersion(count);
        newVersionTextView.setText(String.valueOf(count));
        for (WhatsNew aNew : whatsNewList) {
            aNew.setVersionId(count);
        }
    }

    @Override
    public void addWhatsNew(WhatsNew whatsNew) {
        whatsNewList.add(whatsNew);
        whatsNewAdapter.setWhatsNewList(whatsNewList);

    }

    @Override
    public void onEditWhatsNew(WhatsNew whatsNew) {
        whatsNewList.remove(whatsNew.getId());
        whatsNewList.add(whatsNew.getId(), whatsNew);
        whatsNewAdapter.setWhatsNewList(whatsNewList);
    }

    @Override
    public void showExitConfirm() {
        if (getActivity() != null) {
            ExitDialog.showDialog(getContext(), () -> {
                getActivity().finish();
            });
        }
    }

    @Override
    public void onAddMessage(Version version) {
        TextViewCompat.setCompoundDrawablesRelativeWithIntrinsicBounds(addMessageButton, null, null, ContextCompat.getDrawable(getContext(), R.drawable.ic_edit_pink), null );
        messageEditText.setText(version.getMessage());
    }

    @Override
    public void onEditMessage(Version version) {
        messageEditText.setText(version.getMessage());
    }

    @Override
    public void onSuccess() {
        if (getActivity() != null) {
            getActivity().finish();
        }
    }

    @Override
    public void onWhatsNewListener(WhatsNew whatsNew, boolean isEdit) {
        getPresenter().onAddWhatsNew(whatsNew, isEdit);
    }

    @Override
    public void onMessageListener(Version version, boolean isEdit) {
        getPresenter().onAddMessage(version, isEdit);
    }
}
