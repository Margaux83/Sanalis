package Manager;
import static javax.swing.JOptionPane.showMessageDialog;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import static javax.swing.JOptionPane.showMessageDialog;


public class GestioStockManager extends SqlConnection {
		  
	//Fonction permettant d'insérer les informations du médicament dans la base de données
	public static  void AjoutProduit(String date_peremption, Integer prix, String toxicite, String mg, Integer qte, String type_solution, String description, String laboratoire, String indication, Integer libelle_medoc, String code_medicament, String nom_generique, Integer quantite_stock) {
				Connection cn = getInstance();
		        Statement st;
				 
		        // Vérification si le nom du médicament existe dans la base de données
				try {	
					st = cn.createStatement();
					String Sql1 = "SELECT * FROM medicament Where Name = '" + nom_generique + "'";
			        ResultSet rs1 = st.executeQuery(Sql1);
			        
			        if(rs1.next()) {
		                showMessageDialog(null, "Le nom du médicament existe déja ! Veuillez saisir un autre nom de médicament.");
			        }
			        else {
			    
			    // Vérification si le code du médicament existe dans la base de données    	   
				try {	
					st = cn.createStatement();
					String Sql2 = "SELECT * FROM medicament Where Med_code = '" + code_medicament + "'";
			        ResultSet rs2 = st.executeQuery(Sql2);
			        
			        if(rs2.next()) {
		                showMessageDialog(null, "Le code du médicament saisi existe déja ! Veuillez saisir un autre code du médicament.");
			        	
			        } else {
			        	
			    // Vérification si le libellé du médicament existe dans la base de données    	   
				try {	
					st = cn.createStatement();
					String Sql3 = "SELECT * FROM medicament Where Libelle = '" + libelle_medoc + "'";
			        ResultSet rs3 = st.executeQuery(Sql3);
			        
			        if(rs3.next()) {
		                showMessageDialog(null, "Le libellé du médicament saisi existe déja ! Veuillez saisir un autre libellé du médicament.");
			        	
			        } else {

				if(prix == null || qte == null || libelle_medoc == null) {
					if (date_peremption.isEmpty() || toxicite.isEmpty() || mg.isEmpty() || type_solution.isEmpty() || description.isEmpty() || laboratoire.isEmpty() || indication.isEmpty()
							|| code_medicament.isEmpty() || nom_generique.isEmpty() ) {
			        showMessageDialog(null, "Tous les champs ou un des champs est vide");

					}
		        	showMessageDialog(null, "Tous les champs ou un des champs est vide");
				}
				else {
				try {
					String sql = "INSERT INTO medicament (Name, Med_code, Libelle, Med_use, Laboratory, Description, Med_type, Quantity, Quantity_type, Toxicity, Price, Expi_Date, Quantity_stock) VALUES ('" + nom_generique + "','" 
							+ code_medicament + "','" + libelle_medoc + "','" +indication + "', '" + laboratoire + "', '" + description + "', '" + type_solution + "', '" + qte + "', '" + mg + "','" + toxicite + "','" + prix + "', '" + date_peremption + "', '" + quantite_stock + "')";
					st.executeUpdate(sql);
					showMessageDialog(null, "Le médicament a bien été ajouté !");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}		
				
			}
		  }

			}catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				}
	    }
			}catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				}
			
	    }
			}catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				}
	    }
	
	//Fonction permettant de modifier les informations du médicament dans la base de données
	public static  void ModifierProduit(String ID, String Libelle, String Med_code, String Name, String Expi_date, String Laboratory, String Description, String Quantity, String Quantity_type
    		, String Med_type, String Med_use, String Price, String Toxicity, String Quantity_stock) {
				Connection cn = getInstance();
				Statement st;
				 
		        // Vérification si le nom du médicament existe dans la base de données
				try {	
					st = cn.createStatement();
					String Sql1 = "SELECT * FROM medicament Where Name = '" + Name + "' AND ID!=" +ID;
			        ResultSet rs1 = st.executeQuery(Sql1);
			        
			        if(rs1.next()) {
		                showMessageDialog(null, "Le nom du médicament existe déja ! Veuillez saisir un autre nom de médicament.");
			        }
			        else {
			    
			    // Vérification si le code du médicament existe dans la base de données    	   
				try {	
					st = cn.createStatement();
					String Sql2 = "SELECT * FROM medicament Where Med_code = '" + Med_code + "' AND ID!=" +ID;
			        ResultSet rs2 = st.executeQuery(Sql2);
			        
			        if(rs2.next()) {
		                showMessageDialog(null, "Le code du médicament saisi existe déja ! Veuillez saisir un autre code du médicament.");
			        	
			        } else {
			        	
			    // Vérification si le libellé du médicament existe dans la base de données    	   
				try {	
					st = cn.createStatement();
					String Sql3 = "SELECT * FROM medicament Where Libelle = '" + Libelle + "' AND ID!=" +ID;
			        ResultSet rs3 = st.executeQuery(Sql3);
			        
			        if(rs3.next()) {
		                showMessageDialog(null, "Le libellé du médicament saisi existe déja ! Veuillez saisir un autre libellé du médicament.");
			        	
			        } else {
			        	
			        
					if (ID.isEmpty() || Price.isEmpty() || Quantity.isEmpty() || Libelle.isEmpty() || Expi_date.isEmpty() || Toxicity.isEmpty() || Quantity_type.isEmpty() || Med_type.isEmpty() || Description.isEmpty() || Laboratory.isEmpty() || Med_use.isEmpty()
							|| Med_code.isEmpty() || Name.isEmpty() || Quantity_stock.isEmpty()) {
			        showMessageDialog(null, "Tous les champs ou un des champs est vide");

					}
				else {
				try {
					String sql = "UPDATE `medicament` SET `Libelle`='"+Libelle+"', `Med_code`='"+Med_code+"', `Name`='"+Name+"', `Expi_date`='"+Expi_date+"', `Laboratory`='"+Laboratory+"', `Description`='"+Description+"',`Quantity`='"+Quantity+"', `Quantity_type`='"+Quantity_type+"', `Med_type`='"+Med_type+"', `Med_use`='"+Med_use+"', `Price`='"+Price+"', `Toxicity`='"+Toxicity+"', `Quantity_stock`='"+Quantity_stock+"' WHERE `id` = "+ID;
					st.executeUpdate(sql);
					showMessageDialog(null, "Le médicament a bien été modifié !");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}		
				
			}
		  }
			}catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				}
	    }
			}catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				}
			
	    }
			}catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				}
	    }
		
	
    // Fonction permettant de supprimer une fiche client dans la base de donnÃ©es
    public static  void DeleteProduit(String ID) {
        Connection cn = getInstance();
        int opt = JOptionPane.showConfirmDialog(null,"Êtes-vous sûr de vouloir supprimer le médicament ?", "Effacer",JOptionPane.YES_NO_OPTION);
        if(opt==0) {
            if (ID.isEmpty()) {
            	showMessageDialog(null, "L'ID est vide !");
            }
        else {
            try {
                Statement st = cn.createStatement();
                String sql = "DELETE FROM `medicament` WHERE ID = '" + ID + "'";
                st.executeUpdate(sql);
				showMessageDialog(null, "Le médicament a bien été supprimé!");
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
        }
    }
    
	//Fonction permettant de modifier les informations du salariÃ© avec son ID
	  public static void ModifierProfilStock(String lastname, String firstname, String mail, String password, String phone,Integer id){
			Connection cn = getInstance();
			Statement st;

			// Vérification si le mail existe dans la base de données    	   
			try {	
				st = cn.createStatement();
				String Sql2 = "SELECT * FROM register Where Email = '" + mail + "'";
		        ResultSet rs2 = st.executeQuery(Sql2);
		        
		        if(rs2.next()) {
	                showMessageDialog(null, "Le mail existe déja ! Veuillez saisir un autre mail");
		        	
		        } else {
		        
	        // Vérification si le numéro de téléphone existe dans la base de données
			try {	
				st = cn.createStatement();
				String Sql3 = "SELECT * FROM register Where Phone = '" + phone + "'";
		        ResultSet rs3 = st.executeQuery(Sql3);
		        
		        if(rs3.next()) {
	                showMessageDialog(null, "Le numéro de téléphone portable existe déja ! Veuillez saisir un autre numéro de téléphone portable");
		        }
		        else {	
		        	
		        
			if (phone == null) {
				if (lastname.isEmpty() || firstname.isEmpty() || mail.isEmpty() || password.isEmpty()) {
	            	showMessageDialog(null, "Un des champs est vide !");

				}
            	showMessageDialog(null, "Un des champs est vide !");
			}

			else {
			try {
				String sql = "UPDATE register SET Name='"+ lastname +"', Firstname='"+ firstname +"', Email='"+ mail +"', Password='"+ password +"', Phone = '"+ phone +"'  WHERE ID='"+ id +"'";
				st.executeUpdate(sql);
				showMessageDialog(null, "Modification du profil effectuée avec succès !");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	  }
		}catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			}
	  }
		}catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	  }
  
		//Fonction permettant de modifier les informations du médicament dans la base de données
		public static  void GestionStock(String ID, Integer Spinner_Quantity_stock) {
					Connection cn = getInstance();
						if (ID.isEmpty()) {
				        showMessageDialog(null, "Tous les champs ou un des champs est vide");
						}
						
						if (Spinner_Quantity_stock < 0) {
					        showMessageDialog(null, "Le nombre de quantité doit être supérieure à 0.");
						}
						
					else {
					try {
						if (Spinner_Quantity_stock >= 0 && Spinner_Quantity_stock <= 5) {
					        showMessageDialog(null, "Attention, veuillez ajouter du stock!");
						}
						Statement st = cn.createStatement();
						String sql = "UPDATE `medicament` SET `Quantity_stock`='"+Spinner_Quantity_stock+"' WHERE `id` = "+ID;
						st.executeUpdate(sql);
						showMessageDialog(null, "Le médicament a bien été modifié !");
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}		
					
				}
			  }

	}
