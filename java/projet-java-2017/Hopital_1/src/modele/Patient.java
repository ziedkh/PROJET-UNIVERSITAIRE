
package modele;

import java.io.Serializable;

/**
 * Le patient est enregistré lorsqu'il vient se presenter a l'hopital pour son probleme
 * il doit donner toute les données qui le definisse pour pouvoir completer la fiche medicale
 */
public class Patient implements Serializable{
    
    private String numSecu;//le numero present sur la carte de SS
        public String getNumSecu() {return numSecu;}
        public void setNumSecu(String numSecu) {this.numSecu = numSecu;}
    
    private String nom;//le nom du patient  
        public String getNom() {return nom;}
        public void setNom(String nom) {this.nom = nom;}
    
    private String prenom;// son  prenom
        public String getPrenom() {return prenom;}
        public void setPrenom(String prenom) {this.prenom = prenom;}
    
    private int age;//l'age du ptient
       public int getAge() {return age;}
       public void setAge(int age) {this.age = age;}

    private Boolean sexe;       // 0=homme 1=femme Il n'y a pas de setter car le sexe du patient ne changera jamais
        public Boolean getSexe() {return sexe;} // si le patient change vraiment de sexe, alors il deviendra un nouveau patient

    public Patient(String numSecu, String nom, String prenom, int age, Boolean sexe) {
        this.numSecu = numSecu;
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        this.sexe = sexe;
    }

    public String sexe(){
        if(sexe)return "Femme";
        else return "Homme";
    }
    @Override
    public String toString() {
       if(sexe) return "Mme "+nom;
       else return "Mr "+nom;
    }
    
}

