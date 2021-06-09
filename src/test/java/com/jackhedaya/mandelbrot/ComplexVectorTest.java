package com.jackhedaya.mandelbrot;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ComplexVectorTest {
  // Represents 2 + 10i
  static ComplexVector a = new ComplexVector(2, 10);
  // Represents -4 + 20i
  static ComplexVector b = new ComplexVector(-4, 20);

  static final int WINDOW_WIDTH = 800;
  static final int WINDOW_HEIGHT = 600;

  @Test
  public void addingShouldWork() {

    // Adding should be -2 + 30i
    ComplexVector c = new ComplexVector(-2, 30);

    assertTrue(a.addTo(b).equals(c));
  }

  @Test
  public void multiplyingShouldWork() {
    ComplexVector c = new ComplexVector(-208);

    assertTrue(a.multiplyBy(b).equals(c));
  }

  @Test
  public void toPowerShouldWork() {
    ComplexVector c = new ComplexVector(-96, 40);

    assertTrue("Squaring should equal multiplying by itself", a.toPower(2).equals(a.multiplyBy(a)));
    assertTrue("Squaring should equal correct answer", a.toPower(2).equals(c));
  }

  @Test
  public void fromCanvasShouldWork() {
    ComplexVector.setWindowSize(WINDOW_WIDTH, WINDOW_HEIGHT);

    ComplexVector c = new ComplexVector(10);

    assertTrue("Center of the window should be 0 + 0i",
        ComplexVector.fromCanvasSpace(WINDOW_WIDTH, WINDOW_HEIGHT).equals(new ComplexVector(0)));
    assertTrue("Number in center of height should have no complex component",
        ComplexVector.fromCanvasSpace(300, 300).getComplexComponent() == 0);
    assertTrue("Number in center of width should have no real component",
        ComplexVector.fromCanvasSpace(400, 20).getRealComponent() == 0);
    assertTrue("",
        ComplexVector.fromCanvasSpace(400, 20).getRealComponent() == 0);

  }

  @Test
  public void absShouldWork() {
    // | 2 + 10i | should equal 2 sqrt(26) or 10.19803...

    assertTrue(a.abs() == 2 * Math.sqrt(26));
  }

  @Test
  public void toStringShouldWork() {
    assertTrue(a.toString().equals("2 + 10i"));
  }
}
