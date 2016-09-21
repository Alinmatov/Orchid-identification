package me.cafecode.android.orchididentity.camera;

import android.content.Context;
import android.util.AttributeSet;

import com.google.android.cameraview.CameraView;

/**
 * Created by Natthawut Hemathulin on 9/21/2016 AD.
 * Email: natthawut1991@gmail.com
 */

public class AspectRatioCameraView extends CameraView {
    public AspectRatioCameraView(Context context) {
        super(context);
    }

    public AspectRatioCameraView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        // Make square viewfinder
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        int size = width > height ? height : width;
        setMeasuredDimension(size, size);
    }
}
