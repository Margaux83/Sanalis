package Admin;

import static javax.swing.JOptionPane.showMessageDialog;

import java.awt.BorderLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.JLabel;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.GroupLayout;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.Dialog.ModalityType;
import java.awt.Window.Type;

import javax.swing.JTextField;
import javax.swing.RowFilter;

import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.awt.Dimension;
import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.Toolkit;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class SupprimerMedecin extends javax.swing.JFrame {
	
    private javax.swing.JButton jButton_Delete;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable_Display_Medecin;
    private JTextField jTextField_Id;
    private JTextField jTextField_Name;
    private JTextField jTextField_FirstName;
    private JTextField jTextField_Email;
    private JLabel lblNewLabel_5;
    private JLabel lblNewLabel_6;
    private JTextField jTextField_Profession;
    private JLabel lblNewLabel_11;
	private JDialog frame;
	Toolkit toolkit = Toolkit.getDefaultToolkit();
	Dimension screenSize = toolkit.getScreenSize();
	private JTextField jTextField_Phone;
	private JLabel lblNewLabel;
	private JTextField JtextField_Search;

    public SupprimerMedecin() {
        initComponents();
        Show_Users_In_JTable();
    }
    
   // Connexion à la base de données
   public Connection getConnection()
   {
       Connection con;
       try {
           con = DriverManager.getConnection("jdbc:mysql://localhost/sanalis?useSSL=false", "root","");
           return con;
       } catch (Exception e) {
           e.printStackTrace();
           return null;
       }
   }
        
   // Obtenir une liste des médecins dans la base de données
   public ArrayList<Medecin> getUsersList()
   {
       ArrayList<Medecin> usersList = new ArrayList<Medecin>();
       Connection connection = getConnection();
       
       String query = "SELECT * FROM  `medecin` ";
       Statement st;
       ResultSet rs;
       
       try {
           st = connection.createStatement();
           rs = st.executeQuery(query);
           Medecin user;
           while(rs.next())
           {
               user = new Medecin(rs.getInt("ID"),
            		   rs.getString("Medecin_name"),
            		   rs.getString("Medecin_Firstname"),
            		   rs.getString("Medecin_Email"),
            		   rs.getString("Medecin_Phone"),
            		   rs.getString("Medecin_Profession"));
               usersList.add(user);
           }
       } catch (Exception e) {
           e.printStackTrace();
       }
       return usersList;
   }
   
   // Afficher les données dans JTable
   public void Show_Users_In_JTable()
   {
       ArrayList<Medecin> list = getUsersList();
       DefaultTableModel model = (DefaultTableModel)jTable_Display_Medecin.getModel();
       Object[] row = new Object[7];
       for(int i = 0; i < list.size(); i++)
       {
           row[0] = list.get(i).getId();
           row[1] = list.get(i).getName();
           row[2] = list.get(i).getFirstName();
           row[3] = list.get(i).getEmail();
           row[4] = list.get(i).getPhone();
           row[5] = list.get(i).getProfession();
           model.addRow(row);
       }
    }
                 
   // All Button and design Update
    private void initComponents() {
		frame = new JDialog();
		frame.setModalityType(ModalityType.APPLICATION_MODAL);
		frame.setResizable(false);
		frame.setType(Type.POPUP);
		frame.setBounds(100, 100, 900, 550);
		frame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		int x = (screenSize.width - frame.getWidth()) / 2;
		int y = (screenSize.height - frame.getHeight()) / 2;
		frame.setLocation(x,y);
		
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_Display_Medecin = new javax.swing.JTable();
       
        //btn Modifier 
        jButton_Delete = new javax.swing.JButton();
        jButton_Delete.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
                Manager.AdminManager.DeleteMedecin(jTextField_Id.getText());
                DefaultTableModel model = (DefaultTableModel)jTable_Display_Medecin.getModel();
                model.setRowCount(0);
                Show_Users_In_JTable();
        	}
        });
        jButton_Delete.setText("Supprimer");
       

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(SystemColor.menu);

        jTable_Display_Medecin.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Nom", "Prénom", "Email","Tél","Profession"
            }
        ));
        
        jTable_Display_Medecin.setAutoResizeMode(jTable_Display_Medecin.AUTO_RESIZE_OFF);
        
        jTable_Display_Medecin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_Display_UsersMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable_Display_Medecin);
        
        JLabel lblNewLabel_1 = new JLabel("ID:");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
        
        jTextField_Id = new JTextField();
        jTextField_Id.setColumns(10);
        jTextField_Id.setEditable(false);

        
        JLabel lblNewLabel_2 = new JLabel("Nom:");
        lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
        
        jTextField_Name = new JTextField();
        jTextField_Name.setEditable(false);
        jTextField_Name.setColumns(10);
        
        jTextField_FirstName = new JTextField();
        jTextField_FirstName.setEditable(false);
        jTextField_FirstName.setColumns(10);
        
        JLabel lblNewLabel_3 = new JLabel("Pr\u00E9nom:");
        lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 18));
        
        JLabel lblNewLabel_4 = new JLabel("Email:");
        lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 18));
        
        jTextField_Email = new JTextField();
        jTextField_Email.setEditable(false);
        jTextField_Email.setColumns(10);
        
        lblNewLabel_5 = new JLabel("T\u00E9l:");
        lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 18));
        
        lblNewLabel_6 = new JLabel("Profession:");
        lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 18));
        
        jTextField_Profession = new JTextField();
        jTextField_Profession.setEditable(false);
        jTextField_Profession.setColumns(10);
        
        lblNewLabel_11 = new JLabel("Supprimer un m\u00E9decin");
        lblNewLabel_11.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_11.setFont(new Font("Tahoma", Font.PLAIN, 19));
		
		JButton btnNewButton = new JButton("Retour");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
			}
		});
		
		jTextField_Phone = new JTextField();
		jTextField_Phone.setEditable(false);
		jTextField_Phone.setColumns(10);
		
		lblNewLabel = new JLabel("Recherche");
		
		JtextField_Search = new JTextField();
		JtextField_Search.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				DefaultTableModel model = (DefaultTableModel)jTable_Display_Medecin.getModel();
				String search = JtextField_Search.getText();
				TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(model);
				jTable_Display_Medecin.setRowSorter(tr);
				tr.setRowFilter(RowFilter.regexFilter(search));
			}
		});
		JtextField_Search.setColumns(10);
		
		GroupLayout gl_jPanel1 = new GroupLayout(frame.getContentPane());
		gl_jPanel1.setHorizontalGroup(
			gl_jPanel1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_jPanel1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_jPanel1.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_jPanel1.createSequentialGroup()
							.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(jButton_Delete))
						.addGroup(gl_jPanel1.createSequentialGroup()
							.addGroup(gl_jPanel1.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel_6)
								.addComponent(lblNewLabel_2)
								.addComponent(lblNewLabel_5)
								.addComponent(lblNewLabel_4)
								.addComponent(lblNewLabel_3)
								.addComponent(lblNewLabel_1))
							.addGap(35)
							.addGroup(gl_jPanel1.createParallelGroup(Alignment.LEADING, false)
								.addComponent(jTextField_Phone)
								.addComponent(jTextField_Email)
								.addComponent(jTextField_Profession, Alignment.TRAILING)
								.addComponent(jTextField_Id, GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE)
								.addComponent(jTextField_Name)
								.addComponent(jTextField_FirstName)))
						.addComponent(lblNewLabel_11, GroupLayout.PREFERRED_SIZE, 326, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_jPanel1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_jPanel1.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED, 375, Short.MAX_VALUE)
							.addComponent(lblNewLabel)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(JtextField_Search, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
							.addGap(27))
						.addGroup(gl_jPanel1.createSequentialGroup()
							.addGap(38)
							.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 492, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())))
		);
		gl_jPanel1.setVerticalGroup(
			gl_jPanel1.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_jPanel1.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel_11, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
					.addGap(33)
					.addGroup(gl_jPanel1.createParallelGroup(Alignment.BASELINE)
						.addComponent(jTextField_Id, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1))
					.addGap(11)
					.addGroup(gl_jPanel1.createParallelGroup(Alignment.BASELINE)
						.addComponent(jTextField_Name, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_2))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_jPanel1.createParallelGroup(Alignment.BASELINE)
						.addComponent(jTextField_FirstName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_3))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_jPanel1.createParallelGroup(Alignment.BASELINE)
						.addComponent(jTextField_Email, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_4))
					.addGap(9)
					.addGroup(gl_jPanel1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_5)
						.addComponent(jTextField_Phone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(12)
					.addGroup(gl_jPanel1.createParallelGroup(Alignment.BASELINE)
						.addComponent(jTextField_Profession, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_6))
					.addGap(18)
					.addGroup(gl_jPanel1.createParallelGroup(Alignment.BASELINE)
						.addComponent(jButton_Delete, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(179, Short.MAX_VALUE))
				.addGroup(gl_jPanel1.createSequentialGroup()
					.addContainerGap(45, Short.MAX_VALUE)
					.addGroup(gl_jPanel1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(JtextField_Search, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
       
		frame.getContentPane().setLayout(gl_jPanel1);
        pack();
    }                     

    // afficher les données de ligne jtable dans jtextfields dans l'événement cliqué par la souris
    private void jTable_Display_UsersMouseClicked(java.awt.event.MouseEvent evt) {                                                  
        
        int i = jTable_Display_Medecin.getSelectedRow();
        TableModel model = jTable_Display_Medecin.getModel();
        jTextField_Id.setText(model.getValueAt(i,0).toString());
        jTextField_Name.setText(model.getValueAt(i,1).toString());
        jTextField_FirstName.setText(model.getValueAt(i,2).toString());
        jTextField_Email.setText(model.getValueAt(i,3).toString());
        jTextField_Phone.setText(model.getValueAt(i,4).toString());
        jTextField_Profession.setText(model.getValueAt(i,5).toString());
    }                                                 
                                            
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SupprimerMedecin window = new SupprimerMedecin();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
        });
    } 
}

