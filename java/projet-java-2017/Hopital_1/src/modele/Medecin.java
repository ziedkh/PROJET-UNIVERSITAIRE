
package modele;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * Un Medecin est un Personnel qui a plus de pouvoir, en effet le medecin peux soigné des malade alors qu'un 
 * secrétaire ne pourra que les enregistrer
 * le medecin dispose d'une liste de rendezVous qui lui est propre, cellle des patient qu'il doit traité
 * 
 */
public class Medecin extends Personnel implements Serializable{
    
        

    private ArrayList<RendezVous> listeRdv = new ArrayList<>();
        public ArrayList<RendezVous> getListeRdv() {return listeRdv;}
        public void setListeRdv(ArrayList<RendezVous> listeRdv) {this.listeRdv = listeRdv;}

    
        
    /* a sa creation, le medecin n'a pas encore de rendezVous*/    
    public Medecin(String nom, String prenom, String mdp) {
        super(nom, prenom, mdp);
        
    }  

    @Override
    public boolean isMedecin() {
        return true; 
    }
    
    
    @Override
    public String toString() {
        return "Docteur "+getNom(); 
    }

    @Override
    public boolean supprimer() {
        if(listeRdv.isEmpty())return super.supprimer();
        return false;
        
    }

    
    
      
}
