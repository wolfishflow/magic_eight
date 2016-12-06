/**
 *
 */
package codebusters.magic_eight.openGL;
/**
 * Created by ahmadzam on 2016-11-24.
 */
public final class Maths {

  static final double ONE_EIGHTY_DEGREES = Math.PI;
  static final double THREE_SIXTY_DEGREES = ONE_EIGHTY_DEGREES * 2;
  static final double ONE_TWENTY_DEGREES = THREE_SIXTY_DEGREES / 3;
  static final double NINETY_DEGREES = Math.PI / 2;
  private static final long POWER_CLAMP = 0x00000000ffffffffL;
  private Maths() {
      //EMPTY CONSTRUCTOR
  }


  public static int power(final int base, final int raise) {
    int x = 1;
    long y = raise & POWER_CLAMP;
    long power = base;

    while (y != 0) {
      if ((y & 1) != 0) {
        x *= power;
      }
      y >>>= 1;
      power = power * power;
    }

    return x;
  }
}
