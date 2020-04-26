package Manager;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static javax.swing.JOptionPane.showMessageDialog;


import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class AdminManager extends SqlConnection {

    // Fonction permettant d'ajouter un salari� dans la base de donn�es
    public static void AjoutPersonnel(String Nom, String Prenom, String Portable, String Mail, String MDP, String Role) {
        Connection cn = getInstance();
        Statement st;
        
        // V�rification si le num�ro de t�l�phone existe dans la base de donn�es
		try {	
			st = cn.createStatement();
			String Sql1 = "SELECT * FROM register Where Phone = '" + Portable + "'";
	        ResultSet rs1 = st.executeQuery(Sql1);
	        
	        if(rs1.next()) {
                showMessageDialog(null, "Le num�ro de t�l�phone existe d�ja ! Veuillez saisir un autre num�ro de t�l�phone.");
	        }
	        else {
	    
	    // V�rification si le mail existe dans la base de donn�es    	   
		try {	
			st = cn.createStatement();
			String Sql2 = "SELECT * FROM register Where Email = '" + Mail + "'";
	        ResultSet rs2 = st.executeQuery(Sql2);
	        
	        if(rs2.next()) {
                showMessageDialog(null, "Le mail existe d�ja ! Veuillez saisir un autre mail.");
	        	
	        } else {
	        	
	            if (Nom.isEmpty() || Prenom.isEmpty() || Portable.isEmpty() || Mail.isEmpty() || MDP.isEmpty() || Role.isEmpty()) {
	            	showMessageDialog(null, "Tous les champs ou un des champs est vide");
	            	
	            } else {
	                try {
	                    String sql = "INSERT INTO register (Name, Firstname, Email, Password, Phone, Role)" +
	                            " VALUES ('" + Nom + "','" + Prenom + "','" + Mail + "','" + MDP + "', '" + Portable + "', '" + Role + "')";
	                    st.executeUpdate(sql);
	                    showMessageDialog(null, "Le salari� a �t� ajouter avec succ�s");
	                } catch (SQLException e) {
	                    // TODO Auto-generated catch block
	                    e.printStackTrace();
	                }

	            }
	        	
	        }
	        
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			}
	  }	
        } catch (SQLException e2) {
		// TODO Auto-generated catch block
		e2.printStackTrace();
      }
		
    }
    
    // Fonction permettant de modifier un salari� dans la base de donn�es
    public static void ModifPersonnel(String ID, String Name, String FirstName, String Email, String Password, String Phone, String Role) {
        Connection cn = getInstance();
        Statement st;
        
     // V�rification si le num�ro de t�l�phone existe dans la base de donn�es
 		try {	
 			st = cn.createStatement();
 			String Sql1 = "SELECT * FROM register Where Phone = '" + Phone + "' AND ID!=" +ID;
 	        ResultSet rs1 = st.executeQuery(Sql1);
 	        
 	        if(rs1.next()) {
                 showMessageDialog(null, "Le num�ro de t�l�phone existe d�ja ! Veuillez saisir un autre num�ro de t�l�phone.");
 	        }
 	        else {
    
	    // V�rification si le mail existe dans la base de donn�es    	   
		try {	
			st = cn.createStatement();
			String Sql = "SELECT * FROM register Where Email = '" + Email + "' AND ID!=" +ID;
	        ResultSet rs = st.executeQuery(Sql);
	        
	        if(rs.next()) {
                showMessageDialog(null, "Le mail existe d�ja ! Veuillez saisir un autre mail.");
	        	
	        } else {
	        	
        if (ID.isEmpty() || Name.isEmpty() || FirstName.isEmpty() || Email.isEmpty() || Password.isEmpty() || Phone.isEmpty() || Role.isEmpty()) {
        	showMessageDialog(null, "Tous les champs ou un des champs est vide");
        	
        } else {
            try {
                String sql = "UPDATE `register` SET `Name`='"+Name+"',`Firstname`='"+FirstName+"',`Email`='"+Email+"', `Password`='"+Password+"',`Phone`='"+Phone+"', `Role`='"+Role+"' WHERE `id` = "+ID;
                st.executeUpdate(sql);
                showMessageDialog(null, "Le salari� a bien �t� modifi� ");
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
    }
	        
		}
		
		catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    }
 		}
 		catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    }
    	   
    // // Fonction permettant de supprimer un salari� dans la base de donn�es
    public static  void DeletePersonnel(String ID) {
        Connection cn = getInstance();
        int opt = JOptionPane.showConfirmDialog(null,"�tes-vous s�r de vouloir supprimer le patient ?", "Effacer",JOptionPane.YES_NO_OPTION);
        if(opt==0) {
            if (ID.isEmpty()) {
            	showMessageDialog(null, "L'ID est vide !");
            }
        else {
            try {
                Statement st = cn.createStatement();
                String sql = "DELETE FROM `register` WHERE ID = '" + ID + "'";
                st.executeUpdate(sql);
				showMessageDialog(null, "Le patient a bien �t� supprim�!");
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
        }
    }
    
    // Fonction permettant d'ajouter un m�decin dans la base de donn�es
    public static void AjoutMedecin(String Nom, String Prenom, String Portable, String Mail, String Profession) {
        Connection cn = getInstance();
        Statement st;
        
        // V�rification si le num�ro de t�l�phone existe dans la base de donn�es
		try {	
			st = cn.createStatement();
			String Sql1 = "SELECT * FROM medecin Where Medecin_phone = '" + Portable + "'";
	        ResultSet rs1 = st.executeQuery(Sql1);
	        
	        if(rs1.next()) {
                showMessageDialog(null, "Le num�ro de t�l�phone existe d�ja ! Veuillez saisir un autre num�ro de t�l�phone.");
	        }
	        else {
	        	
	    // V�rification si le mail existe dans la base de donn�es    	   
		try {	
			st = cn.createStatement();
			String Sql2 = "SELECT * FROM medecin Where Medecin_email = '" + Mail + "'";
	        ResultSet rs2 = st.executeQuery(Sql2);
	        
	        if(rs2.next()) {
                showMessageDialog(null, "Le mail existe d�ja ! Veuillez saisir un autre mail.");
	        } else {
	        	
	        
	            if (Nom.isEmpty() || Prenom.isEmpty() || Portable.isEmpty() || Mail.isEmpty() || Profession.isEmpty()) {
	            showMessageDialog(null, "Tous les champs ou un des champs est vide");
        	
        } else {
            try {
                String sql = "INSERT INTO medecin (Medecin_name, Medecin_firstname, Medecin_email, Medecin_phone, Medecin_profession)" +
                        " VALUES ('" + Nom + "','" + Prenom + "','" + Mail + "','" + Portable + "', '" + Profession + "')";
                st.executeUpdate(sql);
                showMessageDialog(null, "Le m�decin a �t� ajouter avec succ�s");
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
	  }
	        
	}
	  catch (SQLException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
		}
	}
	        
	} catch (SQLException e2) {
	  // TODO Auto-generated catch block
	  e2.printStackTrace();
	 	}
   }
    
    // Fonction permettant de modifier un m�decin dans la base de donn�es
    public static void ModifMedecin(String ID, String Name, String FirstName, String Email, String Phone, String Profession) {
        Connection cn = getInstance();
        Statement st;
        
	    // V�rification si le num�ro de t�l�phone existe dans la base de donn�es
		try {	
			st = cn.createStatement();
			String Sql1 = "SELECT * FROM medecin Where Medecin_phone = '" + Phone + "' AND ID!=" +ID;
	        ResultSet rs1 = st.executeQuery(Sql1);
	        
	        if(rs1.next()) {
	            showMessageDialog(null, "Le num�ro de t�l�phone existe d�ja ! Veuillez saisir un autre num�ro de t�l�phone.");
	        }
	        else {
	     
		try {	
			st = cn.createStatement();
			String Sql = "SELECT * FROM medecin Where Medecin_email = '" + Email + "' AND ID!=" +ID;
	        ResultSet rs = st.executeQuery(Sql);
	        
	        if(rs.next()) {
                showMessageDialog(null, "Le mail existe d�ja ! Veuillez saisir un autre mail.");
	        	
	        } else {
	        
        if (ID.isEmpty() || Name.isEmpty() || FirstName.isEmpty() || Email.isEmpty() || Phone.isEmpty() || Profession.isEmpty()) {
        	showMessageDialog(null, "Tous les champs ou un des champs est vide");
        	
        } else {
            try {
                String sql = "UPDATE `medecin` SET `Medecin_name`='"+Name+"',`Medecin_firstname`='"+FirstName+"',`Medecin_email`='"+Email+"', `Medecin_phone`='"+Phone+"', `Medecin_profession`='"+Profession+"' WHERE `id` = "+ID;
                st.executeUpdate(sql);
                showMessageDialog(null, "Le m�decin a bien �t� modifi� ");
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
    }
		
	}
	
	catch (SQLException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
		}
	}
	}
	catch (SQLException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
		}
    }
    
    // // Fonction permettant de supprimer un m�decin dans la base de donn�es
    public static  void DeleteMedecin(String ID) {
        Connection cn = getInstance();
        int opt = JOptionPane.showConfirmDialog(null,"�tes-vous s�r de vouloir supprimer le m�decin ?", "Effacer",JOptionPane.YES_NO_OPTION);
        if(opt==0) {
            if (ID.isEmpty()) {
            	showMessageDialog(null, "L'ID est vide !");
            }
        else {
            try {
                Statement st = cn.createStatement();
                String sql = "DELETE FROM `medecin` WHERE ID = '" + ID + "'";
                st.executeUpdate(sql);
				showMessageDialog(null, "Le m�decin a bien �t� supprim�!");
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
        }
    }
}