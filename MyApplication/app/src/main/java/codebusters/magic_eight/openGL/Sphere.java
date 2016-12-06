package codebusters.magic_eight.openGL;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLUtils;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;

import javax.microedition.khronos.opengles.GL10;

/**
 * Rendering a sphere shape on opengl.
 *
 */
public class Sphere {

  private static final int MAXIMUM_ALLOWED_DEPTH = 5; //MAX DEPTH
  private static final int VERTEX_MAGIC_NUMBER = 5;
  private static final int NUM_FLOATS_PER_VERTEX = 3; //VERTEXES 3D COORDINATE
  private static final int NUM_FLOATS_PER_TEXTURE = 2; //TEXTURE 3D COORDINATE
  private static final int AMOUNT_OF_NUMBERS_PER_VERTEX_POINT = 3;
  private static final int AMOUNT_OF_NUMBERS_PER_TEXTURE_POINT = 2;
  private final List<FloatBuffer> vertexBuffer = new ArrayList<FloatBuffer>(); //BUFFER HOLDING VERTICES
  private final List<float[]> vertices = new ArrayList<float[]>(); //VERTICES FOR THE SPHERE
  private final List<FloatBuffer> textureBuffer = new ArrayList<FloatBuffer>();
  private final List<float[]> texture = new ArrayList<float[]>(); //MAPPING TEXTURE COORDINATES
  private final int[] textures = new int[1]; // TEXTURE POINTER
  private final int totalNumStrips;

  public Sphere(final int depth, final float radius) {

    final int d = Math.max(1, Math.min(MAXIMUM_ALLOWED_DEPTH, depth));


    this.totalNumStrips = Maths.power(2, d - 1) * VERTEX_MAGIC_NUMBER;
    final int numVerticesPerStrip = Maths.power(2, d) * 3;
    final double altitudeStepAngle = Maths.ONE_TWENTY_DEGREES / Maths.power(2, d);
    final double azimuthStepAngle = Maths.THREE_SIXTY_DEGREES / this.totalNumStrips;
    double x, y, z, h, altitude, azimuth;

    for (int stripNum = 0; stripNum < this.totalNumStrips; stripNum++) {
      final float[] vertices = new float[numVerticesPerStrip * NUM_FLOATS_PER_VERTEX];
      final float[] texturePoints = new float[numVerticesPerStrip * NUM_FLOATS_PER_TEXTURE];
      int vertexPos = 0;
      int texturePos = 0;
      altitude = Maths.NINETY_DEGREES;
      azimuth = stripNum * azimuthStepAngle;
      for (int vertexNum = 0; vertexNum < numVerticesPerStrip; vertexNum += 2) {
        // VERTEX: First point
        y = radius * Math.sin(altitude);
        h = radius * Math.cos(altitude);
        z = h * Math.sin(azimuth);
        x = h * Math.cos(azimuth);
        vertices[vertexPos++] = (float) x;
        vertices[vertexPos++] = (float) y;
        vertices[vertexPos++] = (float) z;

        // TEXTURE: First Point
        texturePoints[texturePos++] = (float) (1 - azimuth / Maths.THREE_SIXTY_DEGREES);
        texturePoints[texturePos++] = (float) (1 - (altitude + Maths.NINETY_DEGREES) / Maths.ONE_EIGHTY_DEGREES);

        // VERTEX: Second point
        altitude -= altitudeStepAngle;
        azimuth -= azimuthStepAngle / 2.0;
        y = radius * Math.sin(altitude);
        h = radius * Math.cos(altitude);
        z = h * Math.sin(azimuth);
        x = h * Math.cos(azimuth);
        vertices[vertexPos++] = (float) x;
        vertices[vertexPos++] = (float) y;
        vertices[vertexPos++] = (float) z;

        // VERTEX: Second point
        texturePoints[texturePos++] = (float) (1 - azimuth / Maths.THREE_SIXTY_DEGREES);
        texturePoints[texturePos++] = (float) (1 - (altitude + Maths.NINETY_DEGREES) / Maths.ONE_EIGHTY_DEGREES);

        azimuth += azimuthStepAngle;
      }

      this.vertices.add(vertices);
      this.texture.add(texturePoints);

      ByteBuffer byteBuffer = ByteBuffer.allocateDirect(numVerticesPerStrip * NUM_FLOATS_PER_VERTEX * Float.SIZE);
      byteBuffer.order(ByteOrder.nativeOrder());
      FloatBuffer fb = byteBuffer.asFloatBuffer();
      fb.put(this.vertices.get(stripNum));
      fb.position(0);
      this.vertexBuffer.add(fb);


      byteBuffer = ByteBuffer.allocateDirect(numVerticesPerStrip * NUM_FLOATS_PER_TEXTURE * Float.SIZE);
      byteBuffer.order(ByteOrder.nativeOrder());
      fb = byteBuffer.asFloatBuffer();
      fb.put(this.texture.get(stripNum));
      fb.position(0);
      this.textureBuffer.add(fb);
    }
  }

  public void loadGLTexture(final GL10 gl, final Context context, final int texture) {
    final Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), texture);
    gl.glGenTextures(1, this.textures, 0);
    gl.glBindTexture(GL10.GL_TEXTURE_2D, this.textures[0]);
    gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_NEAREST);
    gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_LINEAR);
    GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, bitmap, 0);
    bitmap.recycle();
  }

  public void draw(final GL10 gl) {

    gl.glBindTexture(GL10.GL_TEXTURE_2D, this.textures[0]);
    gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
    gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
    gl.glFrontFace(GL10.GL_CW);
    for (int i = 0; i < this.totalNumStrips; i++) {
      gl.glVertexPointer(AMOUNT_OF_NUMBERS_PER_VERTEX_POINT, GL10.GL_FLOAT, 0, this.vertexBuffer.get(i));
      gl.glTexCoordPointer(AMOUNT_OF_NUMBERS_PER_TEXTURE_POINT, GL10.GL_FLOAT, 0, this.textureBuffer.get(i));
      gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, this.vertices.get(i).length / AMOUNT_OF_NUMBERS_PER_VERTEX_POINT);
    }

    gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
    gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
  }
}
