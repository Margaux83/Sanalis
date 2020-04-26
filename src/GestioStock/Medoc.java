package GestioStock;


public class Medoc {
    
    private int id;
    private int libelle;
    private String med_code;
    private String name;
    private String expi_date;
    private String laboratory;
    private String description;
    private int quantity;
    private String quantity_type;
    private String med_type;
    private String med_use;
    private int price;
    private String toxicity;
    private int quantity_stock;

    

    
    
    public Medoc(int ID, int Libelle, String Med_code, String Name, String Expi_date, String Laboratory, String Description, int Quantity, String Quantity_type
    		, String Med_type, String Med_use, int Price, String Toxicity, int Quantity_stock)
    {
        this.id = ID;
        this.libelle = Libelle;
        this.med_code = Med_code;
        this.name = Name;
        this.expi_date = Expi_date;
        this.laboratory = Laboratory;
        this.description = Description;
        this.quantity = Quantity;
        this.quantity_type = Quantity_type;
        this.med_type = Med_type;
        this.med_use = Med_use;
        this.price = Price;
        this.toxicity = Toxicity;
        this.quantity_stock = Quantity_stock;


    }
    
    public int getId()
    {
        return id;
    }
    
    public int getLibelle()
    {
        return libelle;
    }
    
    public String getMed_code()
    {
        return med_code;
    }
    
    public String getName()
    {
        return name;
    }
    
    public String getExpi_date()
    {
    	return expi_date;
    }
    
    public String getLaboratory()
    {
    	return laboratory;
    }
    
    public String getDescription()
    {
    	return description;
    }
    
    public int getQuantity()
    {
    	return quantity;
    }
    
    public String getQuantity_type()
    {
    	return quantity_type;
    }
    
    public String getMed_type()
    {
    	return med_type;
    }
    
    public String getMed_use()
    {
    	return med_use;
    }
    
    public int getPrice()
    {
    	return price;
    }
    
    public String getToxicity()
    {
    	return toxicity;
    }
    
    public int getQuantity_stock()
    {
    	return quantity_stock;
    }
}
