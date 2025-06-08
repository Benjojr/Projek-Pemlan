package Entity;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author benja
 */
public class EWallet {

    private Pengguna pengirim;
    private double nominal;
    private String noHpTujuan;
    private String namaWallet;
    private String namaPenerima;

    public EWallet(Pengguna pengirim, String namaWallet, String noHpTujuan, String inputNominal) {
        this.pengirim = pengirim;
        this.namaWallet = namaWallet;
        this.noHpTujuan = noHpTujuan;
        this.namaPenerima = namaPenerima;
        this.nominal = Double.parseDouble(inputNominal.replace(".", ""));
    }

    public Pengguna getPengirim() {
        return pengirim;
    }

    public String getNoHpTujuan(){
        return noHpTujuan;
    }
    
    public String getNamaWallet(){
        return namaWallet;
    }
    
    public String getNamaPenerima(){
        return namaPenerima;
    }
    
    public void setNamaPenerima(String namaPenerima){
        this.namaPenerima = namaPenerima;
    }

    public double getNominal() {
        return nominal;
    }
}
