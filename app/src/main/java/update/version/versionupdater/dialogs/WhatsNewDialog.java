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
import update.version.versionupdater.api.model.WhatsNew;
import update.version.versionupdater.utils.Validation;

public class WhatsNewDialog extends DialogFragment {

    public static final String WHATS_NEW_KEY = "WHATS_NEW_KEY";

    private WhatsNew whatsNew;
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

    public static WhatsNewDialog newInstance(WhatsNew whatsNew) {

        Bundle args = new Bundle();
        args.putParcelable(WHATS_NEW_KEY, whatsNew);
        WhatsNewDialog fragment = new WhatsNewDialog();
        fragment.setArguments(args);
        return fragment;
    }





    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            whatsNew = getArguments().getParcelable(WHATS_NEW_KEY);
        }
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_message, null);
        ButterKnife.bind(this, view);
        if (!TextUtils.isEmpty(whatsNew.getWhatsNewMessage())) { //edit
            isEdit = true;

            textView.setText(R.string.edit_whats_new);
            editText.setText(String.valueOf(whatsNew.getWhatsNewMessage()));


        } else { //new
            textView.setText(R.string.add_whats_new);
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
            whatsNew.setWhatsNewMessage(message);
            listener.onWhatsNewListener(whatsNew, isEdit);
            dismiss();
        }
    }

    public interface OnMessageDialogListener {
        void onWhatsNewListener(WhatsNew whatsNew, boolean isEdit);
    }

    public static void showDialog(FragmentManager fragmentManager, WhatsNew whatsNew, OnMessageDialogListener listener) {
        WhatsNewDialog messageDialog = WhatsNewDialog.newInstance(whatsNew);
        messageDialog.setListener(listener);
        messageDialog.show(fragmentManager, messageDialog.getClass().getSimpleName());
    }
}
