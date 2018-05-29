
package controller;

import launch.ControllerUtils;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import launch.Main;
import modele.Medecin;
import modele.Personnel;

/**
 * FXML Controller class
 */
public class GestionPersonnelController implements Initializable {

    @FXML
    private ListView<Personnel> list;
    @FXML
    private CheckBox checkBox;
    @FXML
    private TextField nom;
    @FXML
    private TextField mdp;
    @FXML
    private TextField prenom;

    ControllerUtils a = new ControllerUtils();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        list.itemsProperty().bind(Main.getHopital().listePersonnel());
    }    

    @FXML
    private void retour(ActionEvent event) throws IOException {
                
        a.borderPaneLoad(EcranLogController.getStage(),"/ihm/Accueil.fxml");
    }

    @FXML
    private void deconnection(ActionEvent event) throws IOException {
        a.deconnection();
    }

    @FXML
    private void suppression(ActionEvent event) {
        if(list.getSelectionModel().getSelectedItem()==null) {
            a.showMessage(Alert.AlertType.ERROR, null, "Veuillez selectionner un RDV. Veuillez recommencer");
        } 
        else {
            
                
            if(a.showMessage(Alert.AlertType.CONFIRMATION, null, "Etes vous sur de vouloir supprimer ?").get()!=ButtonType.OK)    return;
            if(!list.getSelectionModel().getSelectedItem().supprimer())
                a.showMessage(Alert.AlertType.ERROR, null, "le Medecin selectionné a encore des rendezvous. Veuillez recommencer");
        }
        
    }

    @FXML
    private void ajout(ActionEvent event) {
        Personnel p;
        if(nom.getText().equals("") 
                || prenom.getText().equals("") 
                || mdp.getText().equals("")) {
            
            a.showMessage(Alert.AlertType.ERROR, null, "Données invalides");
            return;
        }
        
        if(checkBox.isSelected())p = new Medecin(nom.getText(), prenom.getText(), mdp.getText());
        else p = new Personnel(nom.getText(), prenom.getText(), mdp.getText());
        
        Main.getHopital().getListePersonnel().add(p);
        nom.setText("");prenom.setText("");mdp.setText("");checkBox.setSelected(false);
    }

}
