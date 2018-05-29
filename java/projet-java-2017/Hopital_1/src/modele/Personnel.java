
package modele;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;
import launch.Main;

/**
 *
 * LePersonnel est un employé de l'Hopital, il a un identifiant et un mot de passe qui lui permette d'acceder
 * au logiciel.
 * Il peut créer des Patients, des FicheMedicale et des RendezVous
 * son role dans l'hopital n'est pas de soigner comme un medecin mais on peux le voir comme un
 * secretaire qui s'occupe de l'administration de l'hopital
 * 
 */
public class Personnel implements Serializable{
    private String nom; // le nom de l'employé
        public String getNom() {return nom;}
        public void setNom(String nom) {this.nom = nom;}

    private String prenom;// son prénom
        public String getPrenom() {return prenom;}
        public void setPrenom(String prenom) {this.prenom = prenom;}

    private String identifiant;//l'identifiant est géner automatiquement, il permet au Personnel de se connecter
        public String getIdentifiant() {return identifiant;}      

    private String mdp;// le mot de passe est choisis par l'administrateur lorsqu'il creer un nouveau personnel de la creation du Personnel
        public String getMdp() {return mdp;}
        public void setMdp(String mdp) {this.mdp = mdp;}
        
    /*ArrayList<RendezVous> listeRdv;
        public ArrayList<RendezVous> getListeRdv() {return listeRdv;}
        public void setListeRdv(ArrayList<RendezVous> listeRdv) {this.listeRdv = listeRdv;}
    */
    public Personnel(String nom, String prenom, String mdp) {
        this.nom = nom;
        this.prenom = prenom;        
        this.mdp = mdp;
        /*
        l'identifiant est composé des 2 premieres lettres du prenom et des 6 lettres du nom
        */
        createID();
    }
   
    private void createID(){
        String n=getNom(); //traite le nom
        String p=getPrenom();//traite le prenom        
        String res; //resultat
        int i= 0;
        
        if(getPrenom().length()>=2){
            p=getPrenom().substring(0,2);
        }
        if(getNom().length()>=6){
            n=getNom().substring(0, 6);
        }
        
        res = p+n; 
        res = res.toLowerCase();
        
        while(!verifID(res)) {i++; res = res+i;}
        identifiant=res;
    }
    
    private boolean verifID(String p){
        for(Personnel x : Main.getHopital().getListePersonnel()) if(p.equals(x.getIdentifiant())) return false;
        return true;
    }
    
    public boolean isMedecin(){
        return false;
    }
    
    @Override
    public String toString() {
        return getPrenom()+" "+getNom(); //To change body of generated methods, choose Tools | Templates.
    }


    public boolean supprimer(){
        return Main.getHopital().getListePersonnel().remove(this);
    }
}
