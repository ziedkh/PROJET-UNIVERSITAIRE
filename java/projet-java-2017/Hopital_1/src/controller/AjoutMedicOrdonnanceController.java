package controller;

import controller.OrdonnanceController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import launch.Main;
import modele.Medicament;
import modele.Ordonnance;


public class AjoutMedicOrdonnanceController implements Initializable{

    @FXML
    private ListView<Medicament> list;
    
    private ObservableList<Medicament> listeMedic=FXCollections.observableArrayList();
    private ListProperty<Medicament> listeMedicament = new SimpleListProperty<>(listeMedic);
    
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
    
        
        listeMedic.addAll(Main.getHopital().getListeMedicament());
        
        listeMedic.removeAll(OrdonnanceController.getListeMedicament());
        
        list.itemsProperty().bind(listeMedicament);
        
    }

    @FXML
    private void ajout(ActionEvent event) {
        OrdonnanceController.getListeMedicament().add(list.getSelectionModel().getSelectedItem());
        listeMedic.remove(list.getSelectionModel().getSelectedItem());
        OrdonnanceController.creerListeIntruction();
    }
}
