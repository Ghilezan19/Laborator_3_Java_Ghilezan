package Pb1;

public class Parabola {

    private double a, b, c;

    public Parabola(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public Parabola(Parabola p) {
        this(p.a, p.b, p.c);
    }

    public Varf coordonate() {
        double x = -b / (2 * a);
        double y = (-b * b + 4 * a * c) / (4 * a);
        return new Varf(x, y);
    }

    public static double lungimeSegment(Parabola p1, Parabola p2) {
        Varf v1 = p1.coordonate();
        Varf v2 = p2.coordonate();
        return Math.hypot(v1.getX() - v2.getX(), v1.getY() - v2.getY());
    }

    public void afisare() {
        System.out.println(toString());
    }

    @Override
    public String toString() {
        return String.format("f(x) = %.2fx^2 + %.2fx + %.2f", a, b, c);
    }
}
