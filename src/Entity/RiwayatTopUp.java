/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

/**
 *
 * @author benja
 */
public class RiwayatTopUp extends Riwayat {
    private String noRekPengirim;
    private String noHpPenerima;
    private String jenisEWallet;

    public RiwayatTopUp(String noRekPengirim, String noHpPenerima,
                        String jenisEWallet, double nominal,
                        String namaPengirim, String namaPenerima) {
        super(nominal, namaPengirim, namaPenerima);
        this.noRekPengirim = noRekPengirim;
        this.noHpPenerima = noHpPenerima;
        this.jenisEWallet = jenisEWallet;
    }

    @Override
    public String getJenis() {
        return "TopUp";
    }

    @Override
    public String getDetailTujuan() {
        return noHpPenerima;
    }
    public String getNoRekPengirim() {
        return noRekPengirim;
    }

    public String getJenisEWallet() {
        return jenisEWallet;
    }
}
