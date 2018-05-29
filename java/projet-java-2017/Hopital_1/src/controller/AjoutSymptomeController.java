
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
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import launch.Main;
import modele.Symptome;
import org.omg.CORBA.MARSHAL;

/**
 * FXML Controller class
 */
public class AjoutSymptomeController implements Initializable {

    @FXML
    private ListView<Symptome> list;
    @FXML
    private TextField nom;
    @FXML
    private TextArea description;

    ControllerUtils a = new ControllerUtils();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        list.itemsProperty().bind(Main.getHopital().listeSymptome());
    }    

    @FXML
    private void retour(ActionEvent event) throws IOException {
       
        a.borderPaneLoad(EcranLogController.getStage(), "/ihm/Accueil.fxml");
    }

    @FXML
    private void deconnection(ActionEvent event) throws IOException {
        a.deconnection();
       
    }

    @FXML
    private void ajouter(ActionEvent event) {
        
        if(nom.getText().equals("")){
        
            a.showMessage(Alert.AlertType.ERROR, null, "Données invalides");
            return;
        }
        
        Symptome s = new Symptome(nom.getText(), description.getText());
        Main.getHopital().getListeSymptome().add(s);
        nom.setText("");description.setText("");
        
    }
   
}
