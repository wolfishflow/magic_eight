package codebusters.magic_eight.openGL;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.GLU;

import javax.microedition.khronos.opengles.GL10;

import codebusters.magic_eight.R;

/**
 * Created by ahmadzam on 2016-11-24.
 * REFERENCE: https://developer.android.com/training/graphics/opengl/draw.html
 *  REFERENCE: https://docs.google.com/file/d/0By5c3cOVKbFpVTFpUlFlV2pGVWM/edit?pli=1
 */
public class MyGLRenderer implements GLSurfaceView.Renderer {
    private final float[] mMVPMatrix = new float[16];
    private static final int AXIAL_TILT_DEGRESS = 30;
    private static final float CLEAR_RED = 0.0f;
    private static final float CLEAR_GREEN = 0.0f;
    private static final float CLEAR_BLUE = 0.0f;
    private static final float CLEAR_ALPHA = 0.5f;
    private static final float FOVY = 45.0f;
    private static final float Z_NEAR = 0.1f;
    private static final float Z_FAR = 100.0f;
    private static final float OBJECT_DISTANCE = -15.0f;
    private final Sphere mEarth;
    private final Context mContext;
    private float mRotationAngle;

    public MyGLRenderer(final Context context) {
        this.mContext = context;
        this.mEarth = new Sphere(3, 2);
        this.mRotationAngle = 0.0f;
    }

    public void onDrawFrame(GL10 gl) {
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
        gl.glClearColor(0.1f, 0.2f, 0.7f, 0.0f);
        gl.glLoadIdentity();
        gl.glTranslatef(0.0f, 0.0f, OBJECT_DISTANCE);
        gl.glRotatef(AXIAL_TILT_DEGRESS, 1, 0, 0);
        gl.glRotatef(this.mRotationAngle++, 0, 1, 0);
        this.mEarth.draw(gl);
    }

    @Override
    public void onSurfaceCreated(GL10 gl, javax.microedition.khronos.egl.EGLConfig config) {
        GLES20.glClearColor(0.5f, 0.5f, 0.5f, 0.5f);
        this.mEarth.loadGLTexture(gl, this.mContext, R.drawable.ball);
        gl.glEnable(GL10.GL_TEXTURE_2D);
        gl.glShadeModel(GL10.GL_SMOOTH);
        gl.glClearColor(CLEAR_RED, CLEAR_GREEN, CLEAR_BLUE, CLEAR_ALPHA);
        gl.glClearDepthf(1.0f);
        gl.glEnable(GL10.GL_DEPTH_TEST);
        gl.glDepthFunc(GL10.GL_LEQUAL);
        gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_NICEST);
    }

    public void onSurfaceChanged(GL10 gl, int width, int height) {
        final float aspectRatio = (float) width / (float) (height == 0 ? 1 : height);

        gl.glViewport(0, 0, width, height);
        gl.glMatrixMode(GL10.GL_PROJECTION);
        gl.glLoadIdentity();
        GLU.gluPerspective(gl, FOVY, aspectRatio, Z_NEAR, Z_FAR);
        gl.glMatrixMode(GL10.GL_MODELVIEW);
        gl.glLoadIdentity();

    }

    public static int loadShader(int type, String shaderCode){
        int shader = GLES20.glCreateShader(type);
        GLES20.glShaderSource(shader, shaderCode);
        GLES20.glCompileShader(shader);
        return shader;
    }
}
