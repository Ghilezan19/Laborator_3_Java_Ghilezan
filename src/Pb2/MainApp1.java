package Pb2;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class MainApp1 {

    private static List<Produs> produse = new ArrayList<>();

    public static void main(String[] args) {
        citesteProduse("C:\\Users\\ghile\\IdeaProjects\\Laborator 3 Java\\src\\Pb2\\produse.csv");
        Scanner scanner = new Scanner(System.in);
        boolean run = true;

        while (run) {
            System.out.println("\nMeniu:");
            System.out.println("1. Afisare toate produsele");
            System.out.println("2. Afisare produse expirate");
            System.out.println("3. Vanzare produs");
            System.out.println("4. Afisare produse cu pret minim");
            System.out.println("5. Salvare produse cu cantitate redusa");
            System.out.println("6. Iesire");

            System.out.print("Alege o optiune: ");
            int optiune = scanner.nextInt();
            scanner.nextLine();

            switch (optiune) {
                case 1 -> afisareProduse();
                case 2 -> afisareProduseExpirate();
                case 3 -> vanzareProdus(scanner);
                case 4 -> afisareProdusePretMinim();
                case 5 -> salvareProduseCantitateRedusa(scanner);
                case 6 -> run = false;
                default -> System.out.println("Optiune invalida.");
            }
        }
        scanner.close();
    }

    public static void citesteProduse(String filePath) {
        try (Scanner flux_in = new Scanner(new File(filePath))) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            while (flux_in.hasNextLine()) {
                String linie = flux_in.nextLine();
                String[] str = linie.split(",");
                String produs = str[0];
                double pret = Double.parseDouble(str[1]);
                int cantitate = Integer.parseInt(str[2]);
                LocalDate dataExpirare = LocalDate.parse(str[3], formatter);

                produse.add(new Produs(produs, pret, cantitate, dataExpirare));
            }
        } catch (FileNotFoundException e) {
            System.out.println("Fisierul nu a fost gasit.");
        }
    }

    public static void afisareProduse() {
        produse.forEach(System.out::println);
    }

    public static void afisareProduseExpirate() {
        for (Produs p : produse) {
            if (p.isExpirat()) {
                System.out.println(p);
            }
        }
    }

    public static void vanzareProdus(Scanner scanner) {
        System.out.print("Introduceti numele produsului: ");
        String numeProdus = scanner.nextLine();
        System.out.print("Introduceti cantitatea de vandut: ");
        int cantitate = scanner.nextInt();

        Iterator<Produs> iterator = produse.iterator();
        while (iterator.hasNext()) {
            Produs p = iterator.next();
            if (p.getProdus().equals(numeProdus)) {
                if (p.getCantitate() >= cantitate) {
                    p.vinde(cantitate);
                    System.out.println("Produs vandut. Incasari actuale: " + Produs.incasari);
                    if (p.getCantitate() == 0) {
                        iterator.remove();
                        System.out.println("Produsul a fost eliminat din stoc.");
                    }
                } else {
                    System.out.println("Cantitate insuficienta in stoc.");
                }
                return;
            }
        }
        System.out.println("Produsul nu a fost gasit.");
    }

    public static void afisareProdusePretMinim() {
        OptionalDouble pretMinim = produse.stream().mapToDouble(Produs::getPret).min();
        if (pretMinim.isPresent()) {
            double minPret = pretMinim.getAsDouble();
            produse.stream()
                    .filter(p -> p.getPret() == minPret)
                    .forEach(System.out::println);
        } else {
            System.out.println("Nu exista produse.");
        }
    }

    public static void salvareProduseCantitateRedusa(Scanner scanner) {
        System.out.print("Introduceti valoarea minima a cantitatii: ");
        int cantMin = scanner.nextInt();
        try (PrintWriter flux_out = new PrintWriter("produse_cantitate_sub_valoare.csv")) {
            produse.stream()
                    .filter(p -> p.getCantitate() < cantMin)
                    .forEach(flux_out::println);
            System.out.println("Produsele cu cantitate mai mica decat " + cantMin + " au fost salvate.");
        } catch (IOException e) {
            System.out.println("Eroare la salvarea fisierului.");
        }
    }
}
