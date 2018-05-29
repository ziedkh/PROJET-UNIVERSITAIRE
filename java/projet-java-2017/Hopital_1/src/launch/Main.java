package launch;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;
import modele.FicheMedicale;
import modele.Hopital;


public class Main extends Application {
  
    private static Stage primaryStage;
       public static Stage getPrimaryStage() {return primaryStage;}

    private static Stage stage = new Stage();
        public static Stage getStage() {return stage;}
  
    private static Hopital hopital = new Hopital();
        public static Hopital getHopital() {return hopital;}

    private ControllerUtils a = new ControllerUtils();

    //FONCTION MAIN

    @Override
    public void start(Stage primaryStage) {     //DEMANDER AU PROF COMMENT LANCER UNE SERIALISATION A LA FERMETURE DU PROG
        
        hopital.deserializerListes();
        
        try {            
            BorderPane root = (BorderPane) FXMLLoader.load(getClass().getResource("/ihm/EcranLog.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            

            
        } catch (IOException e) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    @Override
    public void stop(){
        hopital.serializerListes();
        
    }
    public static void main(String[] args) {      
        launch(args);     
    }
}
