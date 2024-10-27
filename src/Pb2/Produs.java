package Pb2;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Produs {
    private String produs;
    private double pret;
    private int cantitate;
    private LocalDate dataExpirare;
    public static double incasari = 0;

    public Produs(String produs, double pret, int cantitate, LocalDate dataExpirare) {
        this.produs = produs;
        this.pret = pret;
        this.cantitate = cantitate;
        this.dataExpirare = dataExpirare;
    }

    public String getProdus() {
        return produs;
    }

    public double getPret() {
        return pret;
    }

    public int getCantitate() {
        return cantitate;
    }

    public LocalDate getDataExpirare() {
        return dataExpirare;
    }

    public void setCantitate(int cantitate) {
        this.cantitate = cantitate;
    }

    public boolean isExpirat() {
        return LocalDate.now().isAfter(dataExpirare);
    }

    public void vinde(int cantitate) {
        if (this.cantitate >= cantitate) {
            this.cantitate -= cantitate;
            incasari += pret * cantitate;
        }
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return produs + " ; " + pret + " ; " + cantitate + " ; " + dataExpirare.format(formatter);
    }
}
