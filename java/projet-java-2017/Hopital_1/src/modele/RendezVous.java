
package modele;

import java.io.Serializable;
import java.time.LocalDate;
import launch.Main;

 
public class RendezVous implements Serializable{
    
    private FicheMedicale fiche;
        public FicheMedicale getFiche() {return this.fiche;}
        public void setFiche(FicheMedicale fiche) {this.fiche = fiche;}    

    private LocalDate date;
        public LocalDate getDate() {return date;}
        public void setDate(LocalDate date) {this.date = date;}
        
    private Heure heure;
        public Heure getHeure() {return heure;}
        public void setHeure(Heure heure) {this.heure = heure;}
    
    
    public RendezVous(FicheMedicale fiche, LocalDate date, Heure heure) {
        this.heure = heure;
        this.date = date;
        this.fiche = fiche;
        
    }

    @Override
    public String toString() {
        return getDate()+" : "+getFiche().getPatient().toString()+" à "+heure.toString(); //To change body of generated methods, choose Tools | Templates.
    }
    
    public Boolean supprimer(){
        for(Personnel e : Main.getHopital().getListePersonnel()){
            if(e.isMedecin()){
                Medecin m = (Medecin) e;
                if(m.getListeRdv().contains(this))
                    m.getListeRdv().remove(this);
                    return true;
            }
        }
        return false;
    }
}