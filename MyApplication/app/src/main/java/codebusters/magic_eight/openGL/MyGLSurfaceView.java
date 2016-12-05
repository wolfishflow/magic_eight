package codebusters.magic_eight.openGL;

import android.content.Context;
import android.opengl.GLSurfaceView;

/**
 * Created by ahmadzam on 2016-11-24.
 */
public class MyGLSurfaceView extends GLSurfaceView {
    private final MyGLRenderer mRenderer;

    public MyGLSurfaceView(Context context) {
        super(context);

        // Create an OpenGL ES 2.0 context
        setEGLContextClientVersion(2);

        mRenderer = new MyGLRenderer();

        // Set the Renderer for drawing on the GLSurfaceView
        setRenderer((Renderer) mRenderer);
    }
}
