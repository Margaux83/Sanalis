package Manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;


public class SqlConnection {


    /**
     * URL de connection
     */
    private static String url = "jdbc:mysql://localhost/sanalis?characterEncoding=latin1&useConfigs=maxPerformance&useSSL=false";

    /**
     * Nom du user
     */
    private static String user = "root";

    /**
     * Mot de passe du user
     */
    private static String passwd = "";


    /**
     * Objet Connection
     */
    private static Connection connect;

    /**
     * Méthode qui permet de réaliser notre connexion à notre
     * base de données
     */

    // Fonction permettant de se connecter à la base de données
    public static Connection getInstance() {
        // Etape 1 : Chargement du driver
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // Etape 2 : recuperation de la connexion
        Connection cn = null;
        try {
            cn = DriverManager.getConnection(url, user, passwd);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return cn;
    }

}


