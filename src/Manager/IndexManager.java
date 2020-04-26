package Manager;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import GestioRdv.MainRdv;
import GestioStock.MainStock;

import static javax.swing.JOptionPane.showMessageDialog;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class IndexManager extends SqlConnection {

	  // Fonction permettant à un salarié de se connecter
	  public static java.sql.ResultSet CoAll(String email,String password, JFrame frameMain) {
		  java.sql.ResultSet rs =null;
			Connection cn = getInstance();
			Integer id = null;
			String log = "";
			String pass = "";
			String role = "";
			try {
				Statement st = cn.createStatement();
				String sql = "SELECT ID, Email, Password, Role FROM register WHERE Email ='" + email + "' AND Password = '" + password + "'";
				rs = st.executeQuery(sql);
				if(rs.next()) {
					id = rs.getInt("ID");
					log = rs.getString("Email");
					pass = rs.getString("Password");
					role = rs.getString("Role");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(log.isEmpty() && pass.isEmpty()) {
				{
					showMessageDialog(null, "Login ou mot de passe incorrect");
				}
			} else {
				if(email.equals(log) && password.equals(pass)) {
					if(role.equals("GESTIOSTOCK")) {
						showMessageDialog(null, "Connexion effectuée en tant que gestionnaire de stock avec succès !");
						GestioStock.MainStock stock = new GestioStock.MainStock(id);
						stock.main(null,id);
						frameMain.dispose();
					} else if(role.equals("GESTIORDV")) {
						showMessageDialog(null, "Connexion effectuée en tant que gestionnaire de RDV avec succès !");
						GestioRdv.MainRdv rdv = new GestioRdv.MainRdv(id);
						rdv.main(null,id);
						frameMain.dispose();
					}
					else if(role.equals("ADMIN")) {
						showMessageDialog(null, "Connexion effectuée en tant qu'administrateur avec succès !");
						Admin.MainAdmin admin = new Admin.MainAdmin();
						admin.main(null);
						frameMain.dispose();
					}
				}
			}
			return rs;
		}
			  
}
