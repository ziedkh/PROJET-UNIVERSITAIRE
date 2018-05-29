
package controller;

import com.sun.deploy.uitoolkit.impl.fx.ui.FXConsole;
import launch.ControllerUtils;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import launch.Main;
import modele.FicheMedicale;

/**
 * FXML Controller class
 */
public class FMController implements Initializable {

    @FXML
    private ListView<FicheMedicale> list;
    
    private static Stage stage;
        public static Stage getStage() {return stage;}
        
        
    ControllerUtils a = new ControllerUtils();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       

        
        list.itemsProperty().bind(Main.getHopital().actualiserFicheTraite());
    }    

    

    @FXML
    private void handleButtonRetour(ActionEvent event) throws IOException{        
        a.borderPaneLoad(EcranLogController.getStage(), "/ihm/Accueil.fxml");
    }
    
     @FXML
    private void handleButtonAjouter(ActionEvent event) throws IOException{
        
        stage=a.borderPaneLoad(EcranLogController.getStage(),new Stage(), "/ihm/CreerFicheMedicale.fxml", false);
    }
    
    @FXML
    private void handleButtonSupprimer(ActionEvent event) throws IOException{
        if(list.getSelectionModel().getSelectedItem()==null) {
            a.showMessage(Alert.AlertType.ERROR, null, "Veuillez selectionner une fiche médicale. Veuillez recommencer");
        } 
        else {
            if (a.showMessage(Alert.AlertType.CONFIRMATION, null, "Etes vous sur de vouloir supprimer ?").get()!=ButtonType.OK)    return;
            Main.getHopital().getListeFicheMedicale().remove(list.getSelectionModel().getSelectedItem());
            Main.getHopital().actualiserFicheTraite();
   
        }  
    }
    

    @FXML
    private void handleButtonLogout(ActionEvent event) throws IOException{
        a.deconnection();
       
    }
    



 
    
}
