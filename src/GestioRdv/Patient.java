package GestioRdv;


public class Patient {
    
    private int id;
    private String patient_name;
    private String patient_firstname;
    private String patient_email;
    private String patient_phone;
    private String patient_phone_fixe;
    private String patient_address;
    private String patient_mutuelle;
    private String patient_secu;
    private String patient_genre;
    private String patient_grpSanguin;
   
    
    public Patient(int ID, String Name, String FirstName, String Email, String Phone, String PhoneFixe, String Address, String Mutuelle, String Secu
    		, String Genre, String GrpSanguin)
    {
        this.id = ID;
        this.patient_name = Name;
        this.patient_firstname = FirstName;
        this.patient_email = Email;
        this.patient_phone = Phone;
        this.patient_phone_fixe = PhoneFixe;
        this.patient_address = Address;
        this.patient_mutuelle = Mutuelle;
        this.patient_secu = Secu;
        this.patient_genre = Genre;
        this.patient_grpSanguin = GrpSanguin;
    }
    
    public int getId()
    {
        return id;
    }
    
    public String getName()
    {
        return patient_name;
    }
    
    public String getFirstName()
    {
        return patient_firstname;
    }
    
    public String getEmail()
    {
        return patient_email;
    }
    
    public String getPhone()
    {
    	return patient_phone;
    }
    
    public String getSecu()
    {
    	return patient_secu;
    }
    
    public String getAddress()
    {
    	return patient_address;
    }
    
    public String getMutuelle()
    {
    	return patient_mutuelle;
    }
    
    public String getPhoneFixe()
    {
    	return patient_phone_fixe;
    }
    
    public String getGenre()
    {
    	return patient_genre;
    }
    
    public String getGrpSanguin()
    {
    	return patient_grpSanguin;
    }
}
