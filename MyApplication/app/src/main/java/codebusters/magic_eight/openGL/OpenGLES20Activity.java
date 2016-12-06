package codebusters.magic_eight.openGL;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;

public class OpenGLES20Activity extends Activity {

    //REFERENCE: https://developer.android.com/training/graphics/opengl/draw.html
    //http://www.programering.com/a/MDM3cjNwATU.html
    private GLSurfaceView mGLView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mGLView = new MyGLSurfaceView(this);
        setContentView(mGLView);
    }

}
