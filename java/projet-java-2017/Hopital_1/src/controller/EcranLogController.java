
package controller;

import launch.ControllerUtils;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import launch.Main;
import modele.FicheMedicale;
import modele.Personnel;

/**
 * FXML Controller class
 */
public class EcranLogController implements Initializable {

    @FXML
    private TextField numDossier;

    @FXML
    private Button boutton;

    @FXML
    private TextField userText;

    @FXML
    private PasswordField mdp;

    @FXML
    private Label erreurNum;

    private static Stage stage;

    public static Stage getStage() {
        return stage;
    }

    private static Personnel personnelConnecte;

    public static Personnel getPersonnelConnecte() {
        return personnelConnecte;
    }

    ControllerUtils a = new ControllerUtils();

    private final String idt = "admin";
    private final String mot = "admin";

    /**
     * Initializes the controller class.
     *
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    public void connexion() throws IOException, Throwable {
        if (userText.getText().equals(idt) & mdp.getText().equals(mot)) {
            personnelConnecte = new Personnel("admin", "", "");

            stage = a.borderPaneLoad(new Stage(), "/ihm/Accueil.fxml");
            Main.getStage().close();
            return;
        }

        for (Personnel p : Main.getHopital().getListePersonnel()) {
            if (userText.getText().equals(p.getIdentifiant()) & mdp.getText().equals(p.getMdp())) {
                personnelConnecte = p;
                stage = a.borderPaneLoad(new Stage(), "/ihm/Accueil.fxml");
                Main.getStage().close();
                return;
            }

        }
        a.showMessage(Alert.AlertType.ERROR, null, "Identifiant ou mot de passe incorrect. Veuillez recommencer");

    }

    @FXML
    public void handleButtonPatient() throws IOException {
       // erreurNum.setVisible(false);
        for (FicheMedicale f : Main.getHopital().getListeFicheMedicale()) {
            if (numDossier.getText().equals(f.getNumDossier())) {

                if (f.getOrdonnance() != null) {
                    OrdonnanceVuePatientController.setFiche(f);
                    stage = a.borderPaneLoad(Main.getStage(), new Stage(), "/ihm/OrdonnanceVuePatient.fxml", true);

                } else {
                    a.showMessage(Alert.AlertType.ERROR, null, f + " n'a pas d'ordonnance a disposition");
                }
            } else {
                erreurNum.setVisible(true);
            }

        }
        numDossier.setText("");
    }
}
