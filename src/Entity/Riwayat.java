package Entity;

import java.time.LocalDate;
import java.time.LocalTime;
public abstract class Riwayat {
    protected double nominal;
    protected String namaPengirim;
    protected String namaPenerima;
    protected LocalDate tanggal;
    protected LocalTime waktu;

    public Riwayat(double nominal, String namaPengirim, String namaPenerima, LocalDate tanggal, LocalTime waktu) {
        this.nominal = nominal;
        this.namaPengirim = namaPengirim;
        this.namaPenerima = namaPenerima;
        this.tanggal = tanggal;
        this.waktu = waktu;
    }

    public abstract String getJenis(); // Transfer / TopUp

    public abstract String getDetailTujuan(); // noRekPenerima atau noHpPenerima

    public double getNominal() {
        return nominal;
    }

    public String getNamaPengirim() {
        return namaPengirim;
    }

    public String getNamaPenerima() {
        return namaPenerima;
    }

    public LocalDate getTanggal() {
        return tanggal;
    }

    public LocalTime getWaktu() {
        return waktu;
    }
}
