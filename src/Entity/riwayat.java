package Entity;

public abstract class Riwayat {
    protected double nominal;
    protected String namaPengirim;
    protected String namaPenerima;

    public Riwayat(double nominal, String namaPengirim, String namaPenerima) {
        this.nominal = nominal;
        this.namaPengirim = namaPengirim;
        this.namaPenerima = namaPenerima;
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
}
