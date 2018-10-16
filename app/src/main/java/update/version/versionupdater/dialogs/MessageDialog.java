package update.version.versionupdater.dialogs;


import android.app.Dialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;
import update.version.versionupdater.R;
import update.version.versionupdater.api.model.Version;
import update.version.versionupdater.utils.Validation;

public class MessageDialog extends DialogFragment {

    public static final String MESSAGE_KEY = "MESSAGE_KEY";
    private Version version;
    private boolean isEdit;
    private OnMessageDialogListener listener;



    @BindView(R.id.edit_name)
    TextInputEditText editText;

    @BindView(R.id.til_name)
    TextInputLayout inputLayout;

    @BindViews( R.id.tv_title)
    TextView textView;

    @BindView(R.id.img_title)
    ImageView imageView;

    @BindView(R.id.cancel_button)
    Button cancelButton;

    @BindView(R.id.confirm_button)
    Button confirmButton;


    public void setListener(OnMessageDialogListener listener) {
        this.listener = listener;
    }

    public static MessageDialog newInstance(Version version) {

        Bundle args = new Bundle();
        args.putParcelable(MESSAGE_KEY, version);
        MessageDialog fragment = new MessageDialog();
        fragment.setArguments(args);
        return fragment;
    }





    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            version = getArguments().getParcelable(MESSAGE_KEY);
        }
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_message, null);
        ButterKnife.bind(this, view);
        if (!TextUtils.isEmpty(version.getMessage())) { //edit
            isEdit = true;

            textView.setText(R.string.edit_message);
            editText.setText(String.valueOf(version.getMessage()));


        } else { //new
            textView.setText(R.string.add_message);
        }


        AlertDialog.Builder builder = new AlertDialog.Builder(getContext(),
                R.style.NewCaseDialogStyle)
                .setView(view)
                .setCancelable(false);
        return builder.create();
    }


    @OnClick(R.id.cancel_button)
    public void cancel() {
        dismiss();
    }

    @OnClick(R.id.confirm_button)
    public void onConfirmClick() {
        String message = String.valueOf(editText.getText());
        if (Validation.validate(message, inputLayout)) {
            version.setMessage(message);
            listener.onMessageListener(version, isEdit);
            dismiss();
        }
    }





    public interface OnMessageDialogListener {
        void onMessageListener(Version version, boolean isEdit);
    }

    public static void showDialog(FragmentManager fragmentManager, Version version, OnMessageDialogListener listener) {
        MessageDialog messageDialog = MessageDialog.newInstance(version);
        messageDialog.setListener(listener);
        messageDialog.show(fragmentManager, messageDialog.getClass().getSimpleName());
    }
}
