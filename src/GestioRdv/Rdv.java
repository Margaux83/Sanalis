package GestioRdv;

import java.sql.Date;

public class Rdv {
	

	 private int ID;
	    private int Id_Patient;
	    private int Id_Medecin;
	    private Date dateChooser;
	    private String combo_heure_debut;
	    private String combo_heure_fin;
	    private String Adresse;
	   
	    public Rdv(int ID, int Id_patient, int Id_medecin, Date Date_RDV, String Begin_hour, String End_hour, String Address_RDV)
	    {
	        this.ID = ID;
	        this.Id_Patient = Id_patient;
	        this.Id_Medecin = Id_medecin;
	        this.dateChooser = Date_RDV;
	        this.combo_heure_debut = Begin_hour;
	        this.combo_heure_fin = End_hour;
	        this.Adresse = Address_RDV;
	    }
	    
	    public int getId()
	    {
	        return ID;
	    }
	    
	    public int getId_Patient()
	    {
	        return Id_Patient;
	    }
	    
	    public int getId_Medecin()
	    {
	        return Id_Medecin;
	    }
	    
	    public Date getDateChooser()
	    {
	        return dateChooser;
	    }
	    
	    public String getCombo_heure_debut()
	    {
	    	return combo_heure_debut;
	    }
	    
	    public String getCombo_heure_fin()
	    {
	    	return combo_heure_fin;
	    }
	    
	    public String getAdresse()
	    {
	    	return Adresse;
	    }
	    
	 
}