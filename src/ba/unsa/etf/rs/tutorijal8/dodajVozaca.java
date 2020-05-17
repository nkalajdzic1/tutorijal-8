package ba.unsa.etf.rs.tutorijal8;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.sql.SQLException;
import java.time.LocalDate;

public class dodajVozaca {

    public Button btnDodajVozaca;
    public TextField fldIme, fldPrezime, fldJMB, fldDatumRodjenja, fldDatumZaposlenja;

    public void initialize() {

        fldIme.textProperty().addListener( (oldVal, obs, newVal) -> {
            if(newVal.length() == 0) fldIme.setStyle("-fx-background-color: rgba(255,93,91,0.98)");
            else fldIme.setStyle("-fx-background-color: #8dfab5");
        });

        fldPrezime.textProperty().addListener( (oldVal, obs, newVal) -> {
            if(newVal.length() == 0) fldPrezime.setStyle("-fx-background-color: rgba(255,93,91,0.98)");
            else fldPrezime.setStyle("-fx-background-color: #8dfab5");
        });

        fldJMB.textProperty().addListener( (oldVal, obs, newVal) -> {
            if(newVal.length() == 0) fldJMB.setStyle("-fx-background-color: rgba(255,93,91,0.98)");
            else fldJMB.setStyle("-fx-background-color: #8dfab5");
        });

        fldDatumRodjenja.textProperty().addListener( (oldVal, obs, newVal) -> {
            if(newVal.matches("[0-9]{4}-[0-9]{2}-[0-9]{2}")) {
                String[] split = newVal.split("-");
                if(Integer.parseInt(split[2]) <= 31 && Integer.parseInt(split[2]) >= 0
                && Integer.parseInt(split[1]) <= 12 && Integer.parseInt(split[1]) >= 1
                && Integer.parseInt(split[0]) >= 1900) fldDatumRodjenja.setStyle("-fx-background-color: #8dfab5");
            } else fldDatumRodjenja.setStyle("-fx-background-color: rgba(255,93,91,0.98)");
        });

        fldDatumZaposlenja.textProperty().addListener( (oldVal, obs, newVal) -> {
            if(newVal.matches("[0-9]{4}-[0-9]{2}-[0-9]{2}")) {
                String[] split = newVal.split("-");
                if(Integer.parseInt(split[2]) <= 31 && Integer.parseInt(split[2]) >= 0
                        && Integer.parseInt(split[1]) <= 12 && Integer.parseInt(split[1]) >= 1
                        && Integer.parseInt(split[0]) >= 1900) fldDatumZaposlenja.setStyle("-fx-background-color: #8dfab5");
            } else fldDatumZaposlenja.setStyle("-fx-background-color: rgba(255,93,91,0.98)");
        });
    }

    public void dodajVozaca() {
        String da = "-fx-background-color: #8dfab5", ne = "-fx-background-color: rgba(255,93,91,0.98)";
        if(fldIme.getStyle().equals(da) && fldPrezime.getStyle().equals(da) &&
           fldJMB.getStyle().equals(da) && fldDatumZaposlenja.getStyle().equals(da) && fldDatumRodjenja.getStyle().equals(da)) {
            Driver driver = new Driver(fldIme.getText(), fldPrezime.getText(), fldJMB.getText(), LocalDate.parse(fldDatumRodjenja.getText()),
                    LocalDate.parse(fldDatumZaposlenja.getText()));
            TransportDAO dao = TransportDAO.getInstance();
            try { dao.addDriver(driver); }
            catch (IllegalArgumentException e) {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setContentText(e.getMessage());
                a.show();
            }
        }
        else {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Niste unijeli validne informacije!");
            a.show();
        }
    }
}
