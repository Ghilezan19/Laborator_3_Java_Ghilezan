package Pb1;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MainApp {

    public static void main(String[] args) {
        List<Parabola> parabole = citireParabole("C:\\Users\\ghile\\IdeaProjects\\Laborator 3 Java\\src\\Pb1\\in.txt");

        for (Parabola p : parabole) {
            p.afisare();
            Varf varf = p.coordonate();
            System.out.println("Varful parabolei: (" + varf.getX() + ", " + varf.getY() + ")");
        }

        if (parabole.size() >= 2) {
            Parabola p1 = parabole.get(0);
            Parabola p2 = parabole.get(1);
            double[] mijloc = mijlocSeg(p1, p2);
            double lungime = Parabola.lungimeSegment(p1, p2);
            System.out.println("Coordonate mijloc segment: (" + mijloc[0] + ", " + mijloc[1] + ")");
            System.out.println("Lungimea segmentului: " + lungime);
        }
    }

    public static List<Parabola> citireParabole(String filePath) {
        List<Parabola> parabole = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String linie;
            while ((linie = br.readLine()) != null) {
                String[] coef = linie.split(" ");
                double a = Double.parseDouble(coef[0]);
                double b = Double.parseDouble(coef[1]);
                double c = Double.parseDouble(coef[2]);
                parabole.add(new Parabola(a, b, c));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return parabole;
    }

    public static double[] mijlocSeg(Parabola p1, Parabola p2) {
        Varf v1 = p1.coordonate();
        Varf v2 = p2.coordonate();
        return new double[] { (v1.getX() + v2.getX()) / 2, (v1.getY() + v2.getY()) / 2 };
    }
}
