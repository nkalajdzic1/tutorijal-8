package ba.unsa.etf.rs.tutorijal8;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

public class dodajAutobus {
    public Slider sliderVozaci;
    public TextField fldProizvodjac, fldSerija, fldSjedista;
    public Button dodajAutobus;

    public void initialize() {

        fldProizvodjac.textProperty().addListener( (oldVal, obs, newVal) -> {
            if(newVal.length() == 0) fldProizvodjac.setStyle("-fx-background-color: rgba(255,93,91,0.98)");
            else fldProizvodjac.setStyle("-fx-background-color: #8dfab5");
        });

        fldSerija.textProperty().addListener( (oldVal, obs, newVal) -> {
            if(newVal.length() == 0) fldSerija.setStyle("-fx-background-color: rgba(255,93,91,0.98)");
            else fldSerija.setStyle("-fx-background-color: #8dfab5");
        });

        fldSjedista.textProperty().addListener( (oldVal, obs, newVal) -> {
            if(!newVal.matches("[0-9]{1,999}")) fldSjedista.setStyle("-fx-background-color: rgba(255,93,91,0.98)");
            else fldSjedista.setStyle("-fx-background-color: #8dfab5");
        });
    }

    public void dodajAutobus(ActionEvent actionEvent) {
        String da = "-fx-background-color: #8dfab5", ne = "-fx-background-color: rgba(255,93,91,0.98)";
        if(fldProizvodjac.getStyle().equals(da) && fldSerija.getStyle().equals(da) && fldSjedista.getStyle().equals(da)) {
            Bus bus = new Bus(fldProizvodjac.getText(), fldSerija.getText(), Integer.parseInt(fldSjedista.getText()));
            TransportDAO dao = TransportDAO.getInstance();
            dao.addBus(bus);
        } else {

        }
    }
}
