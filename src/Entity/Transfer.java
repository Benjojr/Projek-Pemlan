package Entity;

public class Transfer {
    private String noRekTujuan;
    private String bankTujuan;
    private String namaPenerima;
    private Pengguna pengirim;
    private double nominal;

    public Transfer(Pengguna pengirim, String noRekTujuan, String bankTujuan, double nominal) {
        this.pengirim = pengirim;
        this.noRekTujuan = noRekTujuan;
        this.bankTujuan = bankTujuan;
        this.nominal = nominal;
    }
    public String getNoRekTujuan() {
        return noRekTujuan;
    }
    
    public String getBankTujuan() {
        return bankTujuan;
    }

    public double getNominal() {
        return nominal;
    }

    public String getNamaPenerima() {
        return namaPenerima;
    }

    public String getNoRekPenerima() {
        return noRekTujuan;
    }

    public String getBankPenerima() {
        return bankTujuan;
    }
    
    public Pengguna getPengirim(){
        return pengirim;
    }

    public void setNamaPenerima(String namaPenerima) {
        this.namaPenerima = namaPenerima;
    }
}