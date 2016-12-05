package codebusters.magic_eight.openGL;

import android.app.Fragment;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import codebusters.magic_eight.R;

/**
 * Created by alansimon on 2016-12-05.
 */

public class OpenGLFragment extends Fragment{

    //REFERENCE: https://developer.android.com/training/graphics/opengl/draw.html
    //http://www.programering.com/a/MDM3cjNwATU.html
    private GLSurfaceView mGLView;

    public OpenGLFragment()
    {
        super();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mGLView = new MyGLSurfaceView(getContext());

        return mGLView;
    }
}
