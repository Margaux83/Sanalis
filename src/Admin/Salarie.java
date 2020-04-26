package Admin;


public class Salarie {
    
    private int id;
    private String name;
    private String firstname;
    private String email;
    private String password;
    private String phone;
    private String role;
   
    
    public Salarie(int ID, String Name, String FirstName, String Email, String Password, String Phone, String Role)
    {
        this.id = ID;
        this.name = Name;
        this.firstname = FirstName;
        this.email = Email;
        this.password = Password;
        this.phone = Phone;
        this.role = Role;
    }
    
    public int getId()
    {
        return id;
    }
    
    public String getName()
    {
        return name;
    }
    
    public String getFirstName()
    {
        return firstname;
    }
    
    public String getEmail()
    {
        return email;
    }
    
    public String getPassword()
    {
    	return password;
    }
    
    public String getPhone()
    {
    	return phone;
    }
    
    public String getRole()
    {
    	return role;
    }

}
