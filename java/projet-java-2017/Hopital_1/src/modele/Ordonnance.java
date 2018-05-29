
package modele;

import java.io.Serializable;
import java.util.Dictionary;
import java.util.Hashtable;

/**
 * une Ordonnance est une lste de medicament avec leurs instructions definie par le medecin
 */
public class Ordonnance implements Serializable{
    
   
        
    private Dictionary<Medicament, String> dictionary = new Hashtable<>();
    

    public Dictionary<Medicament, String> getDictionary() {
        return dictionary;
    }

    public void setDictionary(Dictionary<Medicament, String> dictionary) {
        this.dictionary = dictionary;
    }
    
    

    public Ordonnance(Dictionary<Medicament, String> m) {
        dictionary=m;
    }

    @Override
    public String toString() {
        return dictionary.keys().toString(); //To change body of generated methods, choose Tools | Templates.
    }

    
    
    
    
}
