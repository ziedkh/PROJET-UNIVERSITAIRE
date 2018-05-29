
package controller;

import launch.ControllerUtils;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.util.converter.NumberStringConverter;
import launch.Main;
import modele.Patient;

/**
 * FXML Controller class
 *
 */
public class CreerPatientController implements Initializable {

    @FXML
    private RadioButton homme;
    @FXML
    private RadioButton femme;
    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private Slider age;
    @FXML
    private TextField numSecu;
    @FXML
    private TextField valeurAge;
    
    ControllerUtils a = new ControllerUtils();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        femme.setSelected(true); 
        
        age.setMin(0); age.setMax(120); age.setBlockIncrement(1); age.setMinorTickCount(1);
        age.setValue(40);
        valeurAge.setText("40");
        
        valeurAge.textProperty().bindBidirectional(age.valueProperty(), new NumberStringConverter());
        valeurAge.setTextFormatter(new TextFormatter<>(t ->{
        
            if (t.isReplaced()) 
                if(t.getText().matches("[^0-9]"))
                    t.setText(t.getControlText().substring(t.getRangeStart(), t.getRangeEnd()));
                

            if (t.isAdded()) {
                if(t.getRangeEnd()<3){
                    if (t.getControlText().contains(",")) {
                        if (t.getText().matches("[^0-9]")) {
                            t.setText("");
                        }
                    } else if (t.getText().matches("[^0-9,]")) {
                        t.setText("");
                    }
                } else t.setText("");
            }
            return t;
        }));
        
        numSecu.setTextFormatter(new TextFormatter<>(t ->{
            
            if (t.isReplaced()) 
                if(t.getText().matches("[^0-9]"))
                        t.setText(t.getControlText().substring(t.getRangeStart(), t.getRangeEnd()));
            
            
            if (t.isAdded()) {
                if (t.getControlText().contains(",")) {
                    if (t.getText().matches("[^0-9]")) {
                        t.setText("");
                    }
                } else if (t.getText().matches("[^0-9,]")) {
                    t.setText("");
                }
                    
            }
            return t;
        })); 
    }
          
    @FXML
    private void actionFemme() {
        homme.setSelected(false);
    }
    
    @FXML
    private void actionHomme() {
        femme.setSelected(false);
    }
    
    @FXML
    private void handleButtonRetour(ActionEvent event) {        
        FMController.getStage().close();
    }

    @FXML
    private void handleButtonValid(ActionEvent event) {
         
        boolean sexe = false;
        if(femme.isSelected()) sexe = true;
            
        if(nom.getText().equals("")) {
            a.showMessage(Alert.AlertType.ERROR, null, "Veuillez entrer un nom.");
        } else{    
            if(prenom.getText().equals("")) { 
                a.showMessage(Alert.AlertType.ERROR, null, "Veuillez entrer un prenom.");
            } else{
                if(numSecu.getLength()!=15){
                    a.showMessage(Alert.AlertType.ERROR, null, "numero securité invalide.");
                } else{
                    if(numSecu.getText().substring(0, 1).matches("[^0-1]")){
                        a.showMessage(Alert.AlertType.ERROR, null, "numéro  securité invalide.");
                    }else{
                        
                        Patient p = new Patient(numSecu.getText(), nom.getText(), prenom.getText(), (int) age.getValue(), sexe);                     
                                              
                        if(a.showMessage(Alert.AlertType.CONFIRMATION, null, "C'est donnÃ©es sont-elles exactes ?\n"
                        + "Nom : "+p.getNom()+" \n"
                        + "prenom : "+p.getPrenom()+"\n"
                        + "age : "+p.getAge()+"\n"
                        + "sexe : "+p.sexe()+"\n"
                        + "numÃ©ro sÃ©cu : "+p.getNumSecu()).get()==ButtonType.OK){
                            Main.getHopital().getListePatient().add(p);
                            FMController.getStage().close();
                        }                            
                    }                                 
                } 
            }
        }
    }
}
