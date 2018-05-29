
package modele;

import java.io.Serializable;
import java.util.ArrayList;
import javafx.collections.ObservableList;

/**
 * Un medicament permet de soigner un ou plusieurs symptomes 
 * 
 */
public class Medicament implements Serializable{
    
    private String nom; //le nom du medicament comment "dolipprane"
        public String getNom() {return nom;}
        public void setNom(String nom) {this.nom = nom;}
   
    private String description;// sa description comme on pourrait la trouver dans sa notice
        public String getDescription() {return description;}
        public void setDescription(String description) {this.description = description;}
    
    private ArrayList<Symptome> listeSymptome;//ceci est la liste des symptomes qui seront soignés par le medicament
        public ArrayList<Symptome> getListeSymptome() {return listeSymptome;}
        public void setListeSymptome(ArrayList<Symptome> listeSymptome) {this.listeSymptome = listeSymptome;}
    
    public Medicament(String nom, String description, ObservableList<Symptome> l) {
        ArrayList<Symptome> listeSymptomeaaz = new ArrayList<>();
        this.nom = nom;
        this.description = description;
        for(Symptome s : l) listeSymptomeaaz.add(s);
        listeSymptome=listeSymptomeaaz;
    }
    
    public boolean soigne(Symptome s){
        
        for(Symptome p : listeSymptome) if(p.equals(s)) return true;
        return false;
    }
    
    @Override
    public String toString() {
        return getNom(); 
    }
    
}
