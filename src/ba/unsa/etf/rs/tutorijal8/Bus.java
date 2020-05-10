package ba.unsa.etf.rs.tutorijal8;

import java.time.LocalDate;
import java.util.ArrayList;

public class Bus {
    private String proizvodjac, serija;
    private int brojSjedista;
    private ArrayList<Driver> vozaci;
    private int id;

    public Bus(int id, String proizvodjac, String serija, int brojSjedista) {
        this.proizvodjac = proizvodjac;
        this.serija = serija;
        this.brojSjedista = brojSjedista;
        this.id = id;
    }

    public Bus(String proizvodjac, String serija, int brojSjedista) {
        this.proizvodjac = proizvodjac;
        this.serija = serija;
        this.brojSjedista = brojSjedista;
    }

    public String getProizvodjac() {
        return proizvodjac;
    }

    public String getSerija() {
        return serija;
    }

    public int getBrojSjedista() {
        return brojSjedista;
    }

    public Object getDriverOne() {
        return new Driver("","","", LocalDate.now().minusDays(1), LocalDate.now().minusDays(1));
    }

    public Object getDriverTwo() {
        return new Driver("","","", LocalDate.now().minusDays(1), LocalDate.now().minusDays(1));
    }

    public String getMaker() {
        return proizvodjac;
    }

    public int getSeatNumber() {
        return brojSjedista;
    }

    public String getSeries() {
        return serija;
    }

    public int getId() {
        return id;
    }
}
