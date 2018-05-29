
package controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import launch.ControllerUtils;
import launch.Main;
import modele.FicheMedicale;
import modele.Heure;
import modele.Medecin;
import modele.Personnel;
import modele.RendezVous;

/**
 * FXML Controller class

 */
public class CreerRDVController implements Initializable {
    
    @FXML
    private ListView<FicheMedicale> list;
    
    @FXML
    private ComboBox<Personnel> cb;
    
    @FXML
    private ComboBox<Heure> cbheure;
    
    @FXML
    private DatePicker datePicker;
    
        
    ControllerUtils a = new ControllerUtils();
    


        

    ListProperty<Personnel> listeproperty = new SimpleListProperty<>(Main.getHopital().getListePersonnel().filtered((t) -> {return t.isMedecin();}));
        public  ObservableList<Personnel> getListePersonnel(){return listeproperty.get();}
        public  void setListePersonnel(ObservableList<Personnel> p){listeproperty.set(p);}
        public  ListProperty<Personnel> listePersonnel(){return listeproperty;}
    
        
     /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        ObservableList<Heure> oh = FXCollections.observableArrayList();
        for (Heure h : Heure.values()) oh.add(h);
        
        list.itemsProperty().bind(Main.getHopital().actualiserFicheTraite());
        cbheure.setItems(oh);  
        cb.itemsProperty().bind(listeproperty);
    } 
    

    @FXML
    private void handleButtonConfirmer(ActionEvent event) {

        if(list.getSelectionModel().getSelectedItem()==null) {
            a.showMessage(Alert.AlertType.ERROR, null, "Veuillez selectionner une Fiche Medicale.");
        } else{    
            if(datePicker.getValue()==null) { 
                a.showMessage(Alert.AlertType.ERROR, null, "Veuillez selectionner une Date.");
            } else{
                if(datePicker.getValue().compareTo(LocalDate.now())<0){
                    a.showMessage(Alert.AlertType.ERROR, null, "Date invalide.");
                } else{
                    if(cbheure.getValue()==null) {
                        a.showMessage(Alert.AlertType.ERROR, null, "Veuillez selectionner une Heure.");
                    } else{
                        if(cb.getValue()==null) {
                            a.showMessage(Alert.AlertType.ERROR, null, "Veuillez selectionner un Medecin.");
                        } 
                        else{
                            RendezVous rdv = new RendezVous(list.getSelectionModel().getSelectedItem(), datePicker.getValue(), cbheure.getValue());
                            
                                                     
                            Medecin m = (Medecin) cb.getValue();
                            m.getListeRdv().add(rdv);
                            
                            for(Personnel p : Main.getHopital().getListePersonnel()){
                                if(!p.isMedecin())continue;
                                Medecin med = (Medecin) p;
                                
                                for(RendezVous r : m.getListeRdv()){
                                    if(!RDVController.getListeRdv().contains(r)) RDVController.getListeRdv().add(r);
                                }
                            }
                            
                            RDVController.getStage().close();
                        }                   
                    }
                            
                        
                }
                    
            }
                
        }
            
    }
        
    
    

    @FXML
    private void handleButtonAnnuler(ActionEvent event) {
        RDVController.getStage().close();
    }
}
