/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

/**
 *
 * @author benja
 */
public class Riwayat {
    private String noRekPengirim;
    private String noRekPenerima;
    private String bankPenerima;
    private String bankPengirim;
    private String noHpPenerima;
    private String jenisEWallet;

    public Riwayat(String noRekPengirim, String noRekPenerima, String bankPenerima, String bankPengirim) {
        this.noRekPengirim = noRekPengirim;
        this.noRekPenerima = noRekPenerima;
        this.bankPenerima = bankPenerima;
        this.bankPengirim = bankPengirim;
        this.noHpPenerima = null;
        this.jenisEWallet = null;
    }

    public Riwayat(String noRekPengirim, String noHpPenerima, String jenisEWallet) {
        this.noRekPengirim = noRekPengirim;
        this.noHpPenerima = noHpPenerima;
        this.jenisEWallet = jenisEWallet;
        this.noRekPenerima = null;
        this.bankPenerima = null;
        this.bankPengirim = null;
    }

    public String getNoRekPengirim() {
        return noRekPengirim;
    }

    public String getNoRekPenerima() {
        return noRekPenerima;
    }
    public String getNoHpPenerima() {
        return noHpPenerima;
    }

    public String getJenisEWallet() {
        return jenisEWallet;
    }

    public String getBankPenerima() {
        return bankPenerima;
    }

    public String getBankPengirim() {
        return bankPengirim;
    }
}