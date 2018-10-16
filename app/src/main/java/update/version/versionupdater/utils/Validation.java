package update.version.versionupdater.utils;

import com.google.android.material.textfield.TextInputLayout;

import update.version.versionupdater.R;

public class Validation {
    public static final int MIN_SIZE = 5;
    public static boolean validate(String text, TextInputLayout inputLayout){
        if(text.isEmpty()){
            inputLayout.setError(inputLayout.getContext().getString(R.string.error_no_text));
            return false;
        }

        if(text.length() < MIN_SIZE){
            inputLayout.setError(inputLayout.getContext().getString(R.string.error_invalid_text));
            return false;
        }

        return true;
    }
}
