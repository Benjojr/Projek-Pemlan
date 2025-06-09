/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author benja
 */
public class RiwayatTransfer extends Riwayat {
    private String noRekPengirim;
    private String noRekPenerima;
    private String bankPengirim;
    private String bankPenerima;

    public RiwayatTransfer(String noRekPengirim, String noRekPenerima,
                           String bankPengirim, String bankPenerima,
                           double nominal, String namaPengirim, String namaPenerima, LocalDate tanggal, LocalTime waktu) {
        super(nominal, namaPengirim, namaPenerima, tanggal, waktu);
        this.noRekPengirim = noRekPengirim;
        this.noRekPenerima = noRekPenerima;
        this.bankPengirim = bankPengirim;
        this.bankPenerima = bankPenerima;
    }

    @Override
    public String getJenis() {
        return "Transfer";
    }

    @Override
    public String getDetailTujuan() {
        return noRekPenerima;
    }

    public String getNoRekPengirim() {
        return noRekPengirim;
    }

    public String getNoRekPenerima() {
        return noRekPenerima;
    }

    public String getBankPengirim() {
        return bankPengirim;
    }

    public String getBankPenerima() {
        return bankPenerima;
    }
}