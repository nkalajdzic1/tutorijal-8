package ba.unsa.etf.rs.tutorijal8;

import java.time.LocalDate;
import java.util.Date;

public class Driver {
    private String ime, prezime, jmb;
    private LocalDate datumRodjenja, datumZaposljenja;

    public Driver(String ime, String prezime, String jmb, LocalDate datumRodjenja, LocalDate datumZaposljenja) {
        this.ime = ime;
        this.prezime = prezime;
        this.jmb = jmb;
        this.datumRodjenja = datumRodjenja;
        this.datumZaposljenja = datumZaposljenja;
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
}
