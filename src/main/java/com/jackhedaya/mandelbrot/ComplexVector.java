package com.jackhedaya.mandelbrot;

public class ComplexVector {
  private static int WINDOW_WIDTH = 600;
  private static int WINDOW_HEIGHT = 300;

  private int canvasX;
  private int canvasY;

  private int a;
  private int b;

  public ComplexVector(int a, int b) {
    this.a = a;
    this.b = b;
  }

  public ComplexVector(int a) {
    this.a = a;
    this.b = 0;
  }

  public static ComplexVector fromCanvasSpace(int x, int y) {
    ComplexVector c = new ComplexVector(x - (WINDOW_WIDTH / 2), y - (WINDOW_HEIGHT / 2));

    c.canvasX = x;
    c.canvasY = y;

    return c;
  }

  public static void setWindowSize(int w, int h) {
    WINDOW_WIDTH = w;
    WINDOW_HEIGHT = h;
  }

  public ComplexVector addTo(ComplexVector v) {
    return new ComplexVector(this.a + v.a, this.b + v.b);
  }

  public ComplexVector toPower(int power) {
    if (power < 1)
      throw new IllegalArgumentException("ComplexVector.toPower only accepts positive integers");

    ComplexVector out = this;

    for (int i = 1; i < power; i++) {
      out = out.multiplyBy(out);
    }

    return out;
  }

  public ComplexVector multiplyBy(ComplexVector v) {
    // Follow equation (a + bi)(c + di) = (ac - bd) + (ad + bc)i

    int realComponent = (this.a * v.a) - (this.b * v.b);
    int imaginaryComponent = (this.a * v.b) + (this.b * v.a);

    return new ComplexVector(realComponent, imaginaryComponent);
  }

  public double abs() {
    return Math.sqrt(Math.pow(this.a, 2) + Math.pow(this.b, 2));
  }

  public int getCanvasX() {
    return this.canvasX;
  }

  public int getCanvasY() {
    return this.canvasY;
  }

  public int getRealComponent() {
    return this.a;
  }

  public int getComplexComponent() {
    return this.b;
  }

  @Override
  public String toString() {
    return String.format("%d + %di", this.a, this.b);
  }

  public boolean equals(ComplexVector other) {
    if (other == null)
      return false;

    if (other == this)
      return true;

    if (!(other instanceof ComplexVector))
      return false;

    ComplexVector o = (ComplexVector) other;

    return this.a == o.a && this.b == o.b;
  }
}
