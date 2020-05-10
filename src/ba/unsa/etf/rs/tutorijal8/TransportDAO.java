package ba.unsa.etf.rs.tutorijal8;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class TransportDAO {

    private ArrayList<Bus> busevi = new ArrayList<>();
    private ArrayList<Driver> vozaci = new ArrayList<>();
    private static TransportDAO instance = null;
    private static Connection connection;
    private PreparedStatement upit1, upit2, statement1;
    
    private TransportDAO() {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:baza.db");
            try {
                upit1 = connection.prepareStatement("SELECT * FROM DRIVERS");
                upit2 = connection.prepareStatement("SELECT * FROM BUSES");
            } catch (SQLException e) {
                regenerateDatabase();
            } finally {
                initializeDrivers();
                initializeBuses();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private void initializeDrivers() {
        try {
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery("SELECT id,ime,prezime,jmb,datumRodjenja,datumZaposlenja FROM DRIVERS");
            while(resultSet.next()) {
                Driver driver = new Driver(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4),
                        LocalDate.parse(resultSet.getString(5),formatter), LocalDate.parse(resultSet.getString(6),formatter) );
                vozaci.add(driver);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void initializeBuses() {
        try {
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery("SELECT id,proizvodjac,serija,brojSjedista,vozac0,vozac1,vozac2 FROM BUSES");
            while(resultSet.next()) {
                Bus bus = new Bus(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getInt(4));
                busevi.add(bus);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void regenerateDatabase() {
        Scanner input;
        try {
            input = new Scanner(new FileInputStream("script.sql"));
            String upit = "";
            while(input.hasNext()) {
                upit += input.nextLine();
                if(upit.length() > 1 && upit.charAt(upit.length()-1) == ';') {
                    try {
                        Statement stmt = connection.createStatement();
                        stmt.execute(upit);
                        upit = "";
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
            input.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static TransportDAO getInstance() {
        if(instance == null) instance = new TransportDAO();
        return instance;
    }

    public void resetDatabase() {
        Scanner ulaz = null;
        try {
            ulaz = new Scanner(new FileInputStream("script.sql"));
            String sqlUpit = "";
            while(ulaz.hasNext()){
                sqlUpit += ulaz.nextLine();
                if(sqlUpit.charAt(sqlUpit.length()-1)==';'){
                    try {
                        Statement stmt = connection.createStatement();
                        stmt.execute(sqlUpit);
                        sqlUpit="";
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }

                }
            }
            ulaz.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static void removeInstance(){
        instance.close();
        instance = null;
    }

    public void close(){
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addDriver(Driver driver) {
        try {
            Statement statemaent = connection.createStatement();
            for(int i=0;i<vozaci.size();i++) {
                if(driver.getJmb() == vozaci.get(i).getJmb()) {
                    throw new IllegalArgumentException("Taj vozač već postoji!");
                }
            }

            statemaent.executeUpdate("INSERT INTO DRIVERS (Ime,Prezime,JMB,DatumRodjenja,DatumZaposlenja) " +
                    "VALUES('" + driver.getName() + "','" + driver.getSurname() + "','" + driver.getJmb() + "','" + driver.getBirthday() +
                     "','" + driver.getDatumZaposlenja() + "')");
            vozaci.add(driver);
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Driver> getDrivers() {
        return vozaci;
    }

    public void addBus(Bus bus) {
        try {
            statement1 = connection.prepareStatement("INSERT INTO BUSES VALUES(?,?,?,?,?,?,?);");
            statement1.setString(2, bus.getProizvodjac());
            statement1.setString(3, bus.getSeries());
            statement1.setInt(4, bus.getSeatNumber());
            statement1.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Bus> getBusses() {

        try {
            ResultSet rez = upit2.executeQuery();
            while(rez.next()) {
                Bus bus = new Bus(rez.getInt(1), rez.getString(2), rez.getString(3), rez.getInt(4));
                busevi.add(bus);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return busevi;
    }

    public void deleteBus(Bus bus) {
        try {
            Statement statemaent = connection.createStatement();
            statemaent.executeUpdate("DELETE FROM BUSES WHERE id = '" + bus.getId() + "'");
            busevi.remove(bus);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteDriver(Driver driver) {
        try {
            Statement statemaent = connection.createStatement();
            statemaent.executeUpdate("DELETE FROM DRIVERS WHERE jmb = '" + driver.getJmb() + "';");
            vozaci.remove(driver);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dodijeliVozacuAutobus(Driver driver, Bus bus, int which) {
        try {
            Statement statemaent = connection.createStatement();
            if(which == 0) {
                statemaent.executeUpdate("UPDATE BUSES SET vozac0 = '" + driver.getId() + "' WHERE id = '" + bus.getId() + "')");
            } else if(which == 1) {
                statemaent.executeUpdate("UPDATE BUSES SET vozac1 = '" + driver.getId() + "' WHERE id = '" + bus.getId() + "')");
            } else if(which == 2) {
                statemaent.executeUpdate("UPDATE BUSES SET vozac2 = '" + driver.getId() + "' WHERE id = '" + bus.getId() + "')");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
