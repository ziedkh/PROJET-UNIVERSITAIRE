
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.ResourceBundle;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import launch.ControllerUtils;
import modele.FicheMedicale;
import modele.Medicament;
import modele.Patient;

/**
 * FXML Controller class
 */
public class OrdonnanceVuePatientController implements Initializable {

    @FXML
    private Label nomPatient;
    @FXML
    private ListView<Medicament> listMedicament;
    @FXML
    private ListView<String> listInstruction;
    
    private ControllerUtils a;
    
    private static FicheMedicale f = null;
        public static void setFiche(FicheMedicale f) {        OrdonnanceVuePatientController.f = f;    }
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<Medicament> medicamentObservable = FXCollections.observableArrayList();
        ListProperty<Medicament> medicaments = new SimpleListProperty<>(medicamentObservable);
        
        ObservableList<String> instructionObservable = FXCollections.observableArrayList();
        ListProperty<String> instructions = new SimpleListProperty<>(instructionObservable);
        
        for (Enumeration<Medicament> e = f.getOrdonnance().getDictionary().keys(); e.hasMoreElements();)
            medicamentObservable.add(e.nextElement());
        
        for (Enumeration<String> e = f.getOrdonnance().getDictionary().elements(); e.hasMoreElements();)
            instructionObservable.add(e.nextElement());
        
        listMedicament.itemsProperty().bind(medicaments);
        listInstruction.itemsProperty().bind(instructions);
        
        
        
        nomPatient.setText(f.getPatient().toString());
        
        
        
    }    
    
      
}
