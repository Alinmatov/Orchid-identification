package me.cafecode.android.orchididentity.api;

import android.content.Context;
import android.content.SharedPreferences;

import me.cafecode.android.orchididentity.R;

/**
 * Created by Natthawut Hemathulin on 12/21/2016 AD.
 * Email: natthawut1991@gmail.com
 */

public class AppPreferences {

    public static final String TYPE_EDGE = "edge";
    public static final String TYPE_COLOR = "color";

    private Context mContext;
    private SharedPreferences mPreferences;

    public AppPreferences(Context context) {
        mContext = context;
        mPreferences = context.getSharedPreferences(
                context.getString(R.string.preference_file_key), Context.MODE_PRIVATE);
    }

    public void setSource(String source) {
        mPreferences.edit()
                .putString(mContext.getString(R.string.preference_source_key), source)
                .apply();
    }

    public String getSource() {
        return mPreferences.getString(
                mContext.getString(R.string.preference_source_key), TYPE_EDGE);
    }
}
