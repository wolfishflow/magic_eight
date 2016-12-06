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
        mRenderer = new MyGLRenderer(context);
        setRenderer((Renderer) mRenderer);
    }
}
