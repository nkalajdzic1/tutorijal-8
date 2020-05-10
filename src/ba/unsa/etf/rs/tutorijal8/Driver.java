package ba.unsa.etf.rs.tutorijal8;

import java.time.LocalDate;

public class Driver {
    private String ime, prezime, jmb;
    private LocalDate datumRodjenja, datumZaposljenja;
    private int id;

    public Driver(String ime, String prezime, String jmb, LocalDate datumRodjenja, LocalDate datumZaposljenja) {
        this.ime = ime;
        this.prezime = prezime;
        this.jmb = jmb;
        this.datumRodjenja = datumRodjenja;
        this.datumZaposljenja = datumZaposljenja;
    }

    public Driver(int id, String ime, String prezime, String jmb, LocalDate datumRodjenja, LocalDate datumZaposljenja) {
        this.ime = ime;
        this.prezime = prezime;
        this.jmb = jmb;
        this.datumRodjenja = datumRodjenja;
        this.datumZaposljenja = datumZaposljenja;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getIme() {
        return ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public String getJmb() {
        return jmb;
    }
    
    public String getName() {
        return ime;
    }

    public LocalDate getBirthday() {
        return datumRodjenja;
    }

    public String getDatumZaposlenja() {
        return datumZaposljenja.toString();
    }

    public String getSurname() {
        return prezime;
    }
}
