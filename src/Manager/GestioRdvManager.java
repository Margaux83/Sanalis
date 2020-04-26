package Manager;

import static javax.swing.JOptionPane.showMessageDialog;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;


import javax.swing.JOptionPane;
import javax.swing.JTextField;


public class GestioRdvManager extends SqlConnection {

    // Fonction permettant d'ajouter une fiche client dans la base de donnÃ©es
    public static  void AjoutPatient(String Nom, String Prenom, String Portable, String Fixe, String Mail, String Adresse, String Num_secu, String Mutuelle, String Sexe, String grpSanguin) {
        Connection cn = getInstance();
        Statement st;

        // Vérification si le numéro de sécu existe dans la base de données
		try {	
			st = cn.createStatement();
			String Sql1 = "SELECT * FROM patient Where Patient_secu = '" + Num_secu + "'";
	        ResultSet rs1 = st.executeQuery(Sql1);
	        
	        if(rs1.next()) {
                showMessageDialog(null, "Le numéro de sécurité sociale existe déja ! Veuillez saisir un autre sécurité social");
	        }
	        else {
	        	
    	// Vérification si le mail existe dans la base de données    	   
		try {	
			st = cn.createStatement();
			String Sql2 = "SELECT * FROM patient Where Patient_email = '" + Mail + "'";
	        ResultSet rs2 = st.executeQuery(Sql2);
	        
	        if(rs2.next()) {
                showMessageDialog(null, "Le mail existe déja ! Veuillez saisir un autre mail");
	        	
	        } else {
	        	
        // Vérification si le numéro de téléphone portable existe dans la base de données
		try {	
			st = cn.createStatement();
			String Sql3 = "SELECT * FROM patient Where Patient_phone = '" + Portable + "'";
	        ResultSet rs3 = st.executeQuery(Sql3);
	        
	        if(rs3.next()) {
                showMessageDialog(null, "Le numéro de téléphone portable existe déja ! Veuillez saisir un autre numéro de téléphone portable");
	        }
	        else {

            if (Nom.isEmpty() || Prenom.isEmpty() || Portable.isEmpty() || Fixe.isEmpty() || Mail.isEmpty() || Adresse.isEmpty()
                    || Num_secu.isEmpty() || Mutuelle.isEmpty() || Sexe.isEmpty() || grpSanguin.isEmpty()) {
            	showMessageDialog(null, "Un des champs est vide !");
            }
        else {
            try {
                String sql = "INSERT INTO patient (Patient_name, Patient_firstname, Patient_email, Patient_phone, Patient_secu, Patient_address, Patient_mutuelle, Patient_phone_fixe, Patient_genre, Patient_grpSanguin)" +
                        " VALUES ('" + Nom + "','"+ Prenom + "','" + Mail + "','" + Portable + "', '" + Num_secu + "', '" + Adresse + "', '" + Mutuelle + "', '" + Fixe + "', '" + Sexe + "', '" + grpSanguin + "')";
                st.executeUpdate(sql);
				showMessageDialog(null, "Le patient a bien été ajouté !");
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
    
    // Fonction permettant de modifier une fiche client dans la base de donnÃ©es
    public static  void UpdatePatient(String ID, String Name, String FirstName, String Email, String Phone, String PhoneFixe, String Address, String Mutuelle, String Secu
    		, String Genre, String GrpSanguin) {
    		Connection cn = getInstance();
    		Statement st;
    		
            // Vérification si le numéro de sécu existe dans la base de données
    		try {	
    			st = cn.createStatement();
    			String Sql1 = "SELECT * FROM patient Where Patient_secu = '" + Secu + "' AND ID!=" +ID;
    	        ResultSet rs1 = st.executeQuery(Sql1);
    	        
    	        if(rs1.next()) {
                    showMessageDialog(null, "Le numéro de sécurité sociale existe déja ! Veuillez saisir un autre sécurité social.");
    	        }
    	        else {
    	        	
        	// Vérification si le mail existe dans la base de données    	   
    		try {	
    			st = cn.createStatement();
    			String Sql2 = "SELECT * FROM patient Where Patient_email = '" + Email + "' AND ID!=" +ID;
    	        ResultSet rs2 = st.executeQuery(Sql2);
    	        
    	        if(rs2.next()) {
                    showMessageDialog(null, "Le mail existe déja ! Veuillez saisir un autre mail.");
    	        	
    	        } else {
    	        	
            // Vérification si le numéro de téléphone portable existe dans la base de données
    		try {	
    			st = cn.createStatement();
    			String Sql3 = "SELECT * FROM patient Where Patient_phone = '" + Phone + "' AND ID!=" +ID;
    	        ResultSet rs3 = st.executeQuery(Sql3);
    	        
    	        if(rs3.next()) {
                    showMessageDialog(null, "Le numéro de téléphone portable existe déja ! Veuillez saisir un autre numéro de téléphone portable.");
    	        }
    	        else {
    		
            if (ID.isEmpty() ||Name.isEmpty() || FirstName.isEmpty() || Email.isEmpty() || Phone.isEmpty() || PhoneFixe.isEmpty() || Address.isEmpty()
                    || Secu.isEmpty() || Mutuelle.isEmpty() || Genre.isEmpty() || GrpSanguin.isEmpty()) {
            	showMessageDialog(null, "Un des champs est vide !");
            }
        else {
            try {
                String sql = "UPDATE `patient` SET `Patient_name`='"+Name+"',`Patient_firstname`='"+FirstName+"',`Patient_email`='"+Email+"', `Patient_phone`='"+Phone+"', `Patient_phone_fixe`='"+PhoneFixe+"', `Patient_address`='"+Address+"',`Patient_mutuelle`='"+Mutuelle+"', `Patient_secu`='"+Secu+"', `Patient_genre`='"+Genre+"', `Patient_grpSanguin`='"+GrpSanguin+"' WHERE `id` = "+ID;
                st.executeUpdate(sql);
				showMessageDialog(null, "Le patient a bien été modifié !");
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
    public static  void DeletePatient(String ID) {
        Connection cn = getInstance();
        int opt = JOptionPane.showConfirmDialog(null,"Êtes-vous sûr de vouloir supprimer le patient ?", "Effacer",JOptionPane.YES_NO_OPTION);
        if(opt==0) {
            if (ID.isEmpty()) {
            	showMessageDialog(null, "L'ID est vide !");
            }
        else {
            try {
                Statement st = cn.createStatement();
                String sql = "DELETE FROM `patient` WHERE ID = '" + ID + "'";
                st.executeUpdate(sql);
				showMessageDialog(null, "Le patient a bien été supprimé!");
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
        }
    }
    
	//Fonction permettant de modifier les informations du salariÃ© avec son ID
	  public static void ModifierProfilRdv(String lastname, String firstname, String mail, String password, String phone,Integer id){
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
	  
	  
		// Fonction permettant d'ajouter un RDV
	    public static  void AjoutRDV(String Id_Patient, String Id_Medecin,String dateRDV, String combo_heure_deb, String Adresse) {
	        Connection cn = getInstance();
	        Statement st;
           if(combo_heure_deb.isEmpty() || Adresse.isEmpty() || Id_Patient.isEmpty() || Id_Medecin.isEmpty()) {
            	showMessageDialog(null, "Un des champs est vide !");
           } else {
	        // Vérification si le rdv existe dans la base de données
			try {	
				st = cn.createStatement();
				String Sql1 = "SELECT * FROM rdv Where Id_Medecin = '" + Id_Medecin + "' and Date_RDV = '" + dateRDV + "' and Begin_hour = '" + combo_heure_deb + "'";
		        ResultSet rs1 = st.executeQuery(Sql1);
		        
		        if(rs1.next()) {
	                showMessageDialog(null, "Le docteur n'est pas disponible à cette heure-là ! Veuillez sélectionner une autre heure.");
		        }
		        else {



	            try {
	                String sql = "INSERT INTO rdv (Id_patient, Id_medecin, Date_RDV, Begin_hour, Address_RDV)" +
	                        " VALUES ('" + Id_Patient + "','"+ Id_Medecin + "','" + dateRDV + "','" + combo_heure_deb + "', '" + Adresse + "')";
	                st.executeUpdate(sql);
					showMessageDialog(null, "Le RDV a bien été enregistré !");
	            } catch (SQLException e) {
	                // TODO Auto-generated catch block
	                e.printStackTrace();
	            }
	               
	        }
	    }catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			}
           }
    
    
}}
