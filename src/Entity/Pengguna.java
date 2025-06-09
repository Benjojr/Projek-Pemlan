package Entity;


public class Pengguna {
    private String pid;
    private String nbid;
    private String namaLengkap;
    private String noRek;
    private double saldo;
    private String bank;
    private String pin;
    private String email;
    private String noHP;
    private String username;

    public Pengguna(String pid, String nbid, String namaLengkap, String noRek, double saldo, String bank, String pin,
                    String email, String noHP, String username) {
        this.pid = pid;
        this.nbid = nbid;
        this.namaLengkap = namaLengkap;
        this.noRek = noRek;
        this.saldo = saldo;
        this.bank = bank;
        this.pin = pin;
        this.email = email;
        this.noHP = noHP;
        this.username = username;
    }

    // Getter dan Setter
    public String getPID() {
        return pid;
    }

    public void setPID(String pid) {
        this.pid = pid;
    }

    public String getNBID() {
        return nbid;
    }

    public void setNBID(String nbid) {
        this.nbid = nbid;
    }

    public String getNamaLengkap() {
        return namaLengkap;
    }

    public void setNamaLengkap(String namaLengkap) {
        this.namaLengkap = namaLengkap;
    }

    public String getNoRek() {
        return noRek;
    }

    public void setNoRek(String noRek) {
        this.noRek = noRek;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String getPIN() {
        return pin;
    }

    public void setPIN(String pin) {
        this.pin = pin;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNoHP() {
        return noHP;
    }

    public void setNoHP(String noHP) {
        this.noHP = noHP;
    }

    public String getBank() {
        return bank;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNamaPertama() {
        return namaLengkap != null ? namaLengkap.split(" ")[0] : "";
    }
}
