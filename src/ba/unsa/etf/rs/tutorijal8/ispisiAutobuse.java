package ba.unsa.etf.rs.tutorijal8;

import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import java.util.ArrayList;

public class ispisiAutobuse {

    public Button btnIzadji;
    public TextArea fldAutobusi;

    public void initialize() {
        TransportDAO dao = TransportDAO.getInstance();
        ArrayList<Bus> lista = dao.getBusses();
        for(Bus bus: lista) fldAutobusi.appendText(bus.getSeries() + " " + bus.getMaker() + " " + bus.getSeatNumber() + '\n');
    }

    public void izadji() { btnIzadji.getScene().getWindow().hide(); }
}
