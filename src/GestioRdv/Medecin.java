package GestioRdv;


public class Medecin {
    
    private int id;
    private String medecin_name;
    private String medecin_firstname;
    private String medecin_email;
    private String medecin_phone;
    private String medecin_profession;

   
    
    public Medecin(int ID, String Name, String FirstName, String Email, String Phone, String Profession)
    {
        this.id = ID;
        this.medecin_name = Name;
        this.medecin_firstname = FirstName;
        this.medecin_email = Email;
        this.medecin_phone = Phone;
        this.medecin_profession = Profession;
    }
    
    public int getId()
    {
        return id;
    }
    
    public String getName()
    {
        return medecin_name;
    }
    
    public String getFirstName()
    {
        return medecin_firstname;
    }
    
    public String getEmail()
    {
        return medecin_email;
    }
    
    public String getPhone()
    {
    	return medecin_phone;
    }
    
    public String getProfession()
    {
    	return medecin_profession;
    }
    
}
