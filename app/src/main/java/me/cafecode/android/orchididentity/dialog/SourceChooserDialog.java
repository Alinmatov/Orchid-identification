package me.cafecode.android.orchididentity.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import me.cafecode.android.orchididentity.R;
import me.cafecode.android.orchididentity.api.AppPreferences;

/**
 * Created by Natthawut Hemathulin on 12/21/2016 AD.
 * Email: natthawut1991@gmail.com
 */

public class SourceChooserDialog extends DialogFragment implements DialogInterface.OnClickListener {

    private AppPreferences mPreferences;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPreferences = new AppPreferences(getContext());
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        int selectedPosition = 0;

        switch (mPreferences.getSource()) {
            case AppPreferences.TYPE_EDGE:
                selectedPosition = 0;
                break;
            case AppPreferences.TYPE_COLOR:
                selectedPosition = 1;
                break;
        }

        return new AlertDialog.Builder(getActivity())
                .setTitle(R.string.identification_type)
                .setSingleChoiceItems(R.array.source_types, selectedPosition, this)
                .setNegativeButton(R.string.close, null)
                .create();
    }

    @Override
    public void onClick(DialogInterface dialogInterface, int position) {

        switch (position) {
            case 0:
                mPreferences.setSource(AppPreferences.TYPE_EDGE);
                break;
            case 1:
                mPreferences.setSource(AppPreferences.TYPE_COLOR);
                break;
        }
    }
}
