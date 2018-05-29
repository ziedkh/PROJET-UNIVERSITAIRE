
package controller;

import launch.ControllerUtils;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import launch.Main;
import modele.FicheMedicale;
import modele.Patient;
import modele.Symptome;

/**
 * FXML Controller class
 */



public class CreerFicheMedicaleController implements Initializable {

    
    @FXML
    private ComboBox<Patient> cbPatient;
    @FXML
    private ComboBox<Symptome> selecSymp;
    @FXML
    private TextArea taMotif;
    @FXML
    private ListView<Symptome> listSymp;
    @FXML
    private TextField champRecherche;
    
    
    private ObservableList<Symptome> ls = FXCollections.observableArrayList();
    private ObservableList<Symptome> listeDansCB = FXCollections.observableArrayList();
    
    private ObservableList<Patient> listP = FXCollections.observableArrayList();
    private ObservableList<Patient> lpat = FXCollections.observableArrayList();
    private final ListProperty<Patient> listePatient = new SimpleListProperty<>(lpat);

    ControllerUtils a = new ControllerUtils();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        listeDansCB.addAll(Main.getHopital().listeSymptome());
        selecSymp.setItems(listeDansCB);       
                
        listP = Main.getHopital().getListePatient();
        
        cbPatient.setOnMouseClicked(new EventHandler<MouseEvent>() {
            
            @Override
            public void handle(MouseEvent event) {
                
                lpat.clear();
                for(Patient p : listP){
                    if(p.getNom().toLowerCase().contains(champRecherche.getText().toLowerCase())){
                        lpat.add(p);
                    }
                }    
            }
        });
        cbPatient.itemsProperty().bind(listePatient);
    }    

    @FXML
    private void handleButtonCreate(ActionEvent event) throws IOException {
        FicheMedicale ficheMedicale = new FicheMedicale(taMotif.getText(),cbPatient.getValue(),ls);
        Main.getHopital().getListeFicheMedicale().add(ficheMedicale);
        Main.getHopital().actualiserFicheTraite();
        FMController.getStage().hide();
    }
    
    @FXML
    private void handleButtonRetour(ActionEvent event) throws IOException {
        FMController.getStage().hide();
    }
    
    @FXML
    private void handleButtonSupp(ActionEvent event) throws IOException {
        if(listSymp.getSelectionModel().getSelectedItem()==null) {
            a.showMessage(Alert.AlertType.ERROR, null, "Veuillez selectionner un Symptôme.");
        } 
        else {
            a.showMessage(Alert.AlertType.CONFIRMATION, null, "Etes vous sur de vouloir supprimer ?");            
            selecSymp.getItems().add(listSymp.getSelectionModel().getSelectedItem());
            ls.remove(listSymp.getSelectionModel().getSelectedIndex()); 
        }
    }
    
    @FXML
    private void handleButtonAddPatient(ActionEvent event) throws IOException{
        a.gridPaneLoad(FMController.getStage(), "/ihm/CreerPatient.fxml");
    }
    
    @FXML
    private void handleButtonOKSymp(ActionEvent event) throws IOException{
        ls.add(selecSymp.getSelectionModel().getSelectedItem());
        listeDansCB.remove(selecSymp.getSelectionModel().getSelectedIndex());
        listSymp.setItems(ls);                  
        selecSymp.getSelectionModel().clearSelection();
    }
}
