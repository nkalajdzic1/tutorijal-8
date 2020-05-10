package ba.unsa.etf.rs.tutorijal8;

import java.util.ArrayList;

public class TransportDAO {

    ArrayList<Bus> busevi;
    ArrayList<Driver> vozaci;

    public static TransportDAO getInstance() {
        return new TransportDAO();
    }

    public void resetDatabase() {
    }

    public void addDriver(Driver driver) {
    }

    public ArrayList<Driver> getDrivers() {
        return vozaci;
    }

    public void addBus(Bus bus) {
    }

    public ArrayList<Bus> getBusses() {
        return busevi;
    }

    public void deleteBus(Bus bus) {
    }

    public void deleteDriver(Driver driver) {
    }

    public void dodijeliVozacuAutobus(Driver driver, Bus bus, int which) {
    }
}
