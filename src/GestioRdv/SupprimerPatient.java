package GestioRdv;

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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class SupprimerPatient extends javax.swing.JFrame {
	
    private javax.swing.JButton jButton_Delete;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable_Display_Patient;
    private JLabel lblNewLabel;
    private JComboBox comboBox_genre;
    private JComboBox comboBox_grpSanguin;
    private JTextField jTextField_Id;
    private JTextField jTextField_Name;
    private JTextField jTextField_FirstName;
    private JTextField jTextField_Email;
    private JLabel lblNewLabel_5;
    private JTextField jTextField_Phone;
    private JLabel lblNewLabel_6;
    private JTextField jTextField_Phone_fixe;
    private JLabel lblNewLabel_7;
    private JTextField jTextField_Address;
    private JTextField jTextField_Mutuelle;
    private JTextField jTextField_Secu;
    private JLabel lblNewLabel_11;
	private JDialog frame;
	Toolkit toolkit = Toolkit.getDefaultToolkit();
	Dimension screenSize = toolkit.getScreenSize();
	private JLabel lblNewLabel_12;
	private JTextField JtextField_Search;

    public SupprimerPatient() {
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
        
   // Obtenir une liste d'utilisateurs de la base de données
   public ArrayList<Patient> getUsersList()
   {
       ArrayList<Patient> usersList = new ArrayList<Patient>();
       Connection connection = getConnection();
       
       String query = "SELECT * FROM  `patient` ";
       Statement st;
       ResultSet rs;
       
       try {
           st = connection.createStatement();
           rs = st.executeQuery(query);
           Patient user;
           while(rs.next())
           {
               user = new Patient(rs.getInt("ID"),
            		   rs.getString("Patient_name"),
            		   rs.getString("Patient_firstname"),
            		   rs.getString("Patient_email"),
            		   rs.getString("Patient_phone"),
            		   rs.getString("Patient_phone_fixe"),
            		   rs.getString("Patient_address"),
            		   rs.getString("Patient_mutuelle"),
            		   rs.getString("Patient_secu"),
            		   rs.getString("Patient_genre"),
            		   rs.getString("Patient_grpSanguin"));
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
       ArrayList<Patient> list = getUsersList();
       DefaultTableModel model = (DefaultTableModel)jTable_Display_Patient.getModel();
       Object[] row = new Object[11];
       for(int i = 0; i < list.size(); i++)
       {
           row[0] = list.get(i).getId();
           row[1] = list.get(i).getName();
           row[2] = list.get(i).getFirstName();
           row[3] = list.get(i).getEmail();
           row[4] = list.get(i).getPhone();
           row[5] = list.get(i).getPhoneFixe();
           row[6] = list.get(i).getAddress();
           row[7] = list.get(i).getMutuelle();
           row[8] = list.get(i).getSecu();
           row[9] = list.get(i).getGenre();
           row[10] = list.get(i).getGrpSanguin();
           model.addRow(row);
       }
    }
                 
   // All Button and design Delete
    private void initComponents() {
		frame = new JDialog();
		frame.setModalityType(ModalityType.APPLICATION_MODAL);
		frame.setResizable(false);
		frame.setType(Type.POPUP);
		frame.setBounds(100, 100, 900, 625);
		frame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		int x = (screenSize.width - frame.getWidth()) / 2;
		int y = (screenSize.height - frame.getHeight()) / 2;
		frame.setLocation(x,y);
		
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_Display_Patient = new javax.swing.JTable();
       
        //Update Button 
        jButton_Delete = new javax.swing.JButton();
        jButton_Delete.setText("Supprimer");
        jButton_Delete.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
                            Manager.GestioRdvManager.DeletePatient(jTextField_Id.getText());
                            DefaultTableModel model = (DefaultTableModel)jTable_Display_Patient.getModel();
                            model.setRowCount(0);
                            Show_Users_In_JTable();
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(SystemColor.menu);

        jTable_Display_Patient.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Nom", "Prénom", "Email","Tél Mobile","Tél Fixe","Adresse","Mutuelle","Sécurité Sociale","Genre","Groupe Sanguin"
            }
        ));
        
        jTable_Display_Patient.setAutoResizeMode(jTable_Display_Patient.AUTO_RESIZE_OFF);
        
        jTable_Display_Patient.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_Display_UsersMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable_Display_Patient);

        lblNewLabel = new JLabel("Genre:");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
        


        
        JLabel lblNewLabel_1 = new JLabel("ID:");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
        
        jTextField_Id = new JTextField();
        jTextField_Id.setColumns(10);
        jTextField_Id.setEditable(false);
        
        JLabel lblNewLabel_2 = new JLabel("Nom:");
        lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
        
        jTextField_Name = new JTextField();
        jTextField_Name.setColumns(10);
        jTextField_Name.setEditable(false);
        
        jTextField_FirstName = new JTextField();
        jTextField_FirstName.setColumns(10);
        jTextField_FirstName.setEditable(false);

       
        JLabel lblNewLabel_3 = new JLabel("Pr\u00E9nom:");
        lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 18));
        
        JLabel lblNewLabel_4 = new JLabel("Email:");
        lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 18));
        
        jTextField_Email = new JTextField();
        jTextField_Email.setColumns(10);
        jTextField_Email.setEditable(false);

        
        lblNewLabel_5 = new JLabel("T\u00E9l Mobile:");
        lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 18));
        
        jTextField_Phone = new JTextField();
        jTextField_Phone.setColumns(10);
        jTextField_Phone.setEditable(false);

        
        lblNewLabel_6 = new JLabel("T\u00E9l Fixe:");
        lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 18));
        
        jTextField_Phone_fixe = new JTextField();
        jTextField_Phone_fixe.setColumns(10);
        jTextField_Phone_fixe.setEditable(false);

        
        lblNewLabel_7 = new JLabel("Adresse:");
        lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 18));
        
        jTextField_Address = new JTextField();
        jTextField_Address.setColumns(10);
        jTextField_Address.setEditable(false);

        
        JLabel lblNewLabel_8 = new JLabel("Mutuelle:");
        lblNewLabel_8.setFont(new Font("Tahoma", Font.PLAIN, 18));
        
        jTextField_Mutuelle = new JTextField();
        jTextField_Mutuelle.setColumns(10);
        jTextField_Mutuelle.setEditable(false);

        
        JLabel lblNewLabel_9 = new JLabel("S\u00E9cu:");
        lblNewLabel_9.setFont(new Font("Tahoma", Font.PLAIN, 18));
        
        jTextField_Secu = new JTextField();
        jTextField_Secu.setColumns(10);
        jTextField_Secu.setEditable(false);

        
        JLabel lblNewLabel_10 = new JLabel("Groupe Sanguin:");
        lblNewLabel_10.setFont(new Font("Tahoma", Font.PLAIN, 18));
        
        comboBox_genre = new JComboBox();
        comboBox_genre.setModel(new DefaultComboBoxModel(new String[] {"Homme ", "Femme ", "Autres"}));
        comboBox_genre.setEnabled(false);

        comboBox_grpSanguin = new JComboBox();
        comboBox_grpSanguin.setModel(new DefaultComboBoxModel(new String[] {"A-", "A+", "AB-", "AB+", "B-", "B+", "O-", "O+"}));
        comboBox_grpSanguin.setEnabled(false);
        
        lblNewLabel_11 = new JLabel("Supprimer un patient");
        lblNewLabel_11.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_11.setFont(new Font("Tahoma", Font.PLAIN, 19));
		
		JButton btnNewButton = new JButton("Retour");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
			}
		});
		
		lblNewLabel_12 = new JLabel("Recherche");
		
		JtextField_Search = new JTextField();
		JtextField_Search.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				DefaultTableModel model = (DefaultTableModel)jTable_Display_Patient.getModel();
				String search = JtextField_Search.getText();
				TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(model);
				jTable_Display_Patient.setRowSorter(tr);
				tr.setRowFilter(RowFilter.regexFilter(search));
			}
		});
		JtextField_Search.setColumns(10);
		GroupLayout gl_jPanel1 = new GroupLayout(frame.getContentPane());
		gl_jPanel1.setHorizontalGroup(
			gl_jPanel1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_jPanel1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_jPanel1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_jPanel1.createSequentialGroup()
							.addGroup(gl_jPanel1.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_jPanel1.createParallelGroup(Alignment.LEADING)
									.addGroup(gl_jPanel1.createSequentialGroup()
										.addGroup(gl_jPanel1.createParallelGroup(Alignment.LEADING)
											.addGroup(gl_jPanel1.createSequentialGroup()
												.addGroup(gl_jPanel1.createParallelGroup(Alignment.LEADING)
													.addComponent(lblNewLabel_6)
													.addComponent(lblNewLabel_2)
													.addComponent(lblNewLabel)
													.addComponent(lblNewLabel_5)
													.addComponent(lblNewLabel_4)
													.addComponent(lblNewLabel_8)
													.addComponent(lblNewLabel_9)
													.addComponent(lblNewLabel_3)
													.addComponent(lblNewLabel_1))
												.addGap(35)
												.addGroup(gl_jPanel1.createParallelGroup(Alignment.LEADING)
													.addComponent(comboBox_genre, 0, 219, Short.MAX_VALUE)
													.addGroup(gl_jPanel1.createParallelGroup(Alignment.TRAILING, false)
														.addComponent(jTextField_Phone, Alignment.LEADING)
														.addComponent(jTextField_Email, Alignment.LEADING)
														.addComponent(jTextField_Secu)
														.addComponent(jTextField_Mutuelle, Alignment.LEADING)
														.addComponent(jTextField_Address, Alignment.LEADING)
														.addComponent(jTextField_Phone_fixe)
														.addComponent(jTextField_Id, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE)
														.addComponent(jTextField_Name, Alignment.LEADING)
														.addComponent(jTextField_FirstName, Alignment.LEADING))))
											.addGroup(gl_jPanel1.createSequentialGroup()
												.addComponent(lblNewLabel_10)
												.addPreferredGap(ComponentPlacement.UNRELATED)
												.addComponent(comboBox_grpSanguin, 0, 194, Short.MAX_VALUE)))
										.addPreferredGap(ComponentPlacement.RELATED, 31, Short.MAX_VALUE))
									.addGroup(gl_jPanel1.createSequentialGroup()
										.addComponent(lblNewLabel_7)
										.addPreferredGap(ComponentPlacement.RELATED)))
								.addGroup(gl_jPanel1.createSequentialGroup()
									.addComponent(lblNewLabel_11, GroupLayout.PREFERRED_SIZE, 326, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED))
								.addGroup(gl_jPanel1.createSequentialGroup()
									.addGap(113)
									.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(jButton_Delete)
									.addGap(51)))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 510, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(Alignment.TRAILING, gl_jPanel1.createSequentialGroup()
							.addComponent(lblNewLabel_12)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(JtextField_Search, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(24))))
		);
		gl_jPanel1.setVerticalGroup(
			gl_jPanel1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_jPanel1.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel_11, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
					.addGap(4)
					.addGroup(gl_jPanel1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_12)
						.addComponent(JtextField_Search, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_jPanel1.createParallelGroup(Alignment.LEADING)
						.addComponent(jScrollPane1, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 474, Short.MAX_VALUE)
						.addGroup(gl_jPanel1.createSequentialGroup()
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
								.addComponent(jTextField_Phone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_5))
							.addGap(12)
							.addGroup(gl_jPanel1.createParallelGroup(Alignment.BASELINE)
								.addComponent(jTextField_Phone_fixe, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_6))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_jPanel1.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_7)
								.addComponent(jTextField_Address, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_jPanel1.createParallelGroup(Alignment.BASELINE)
								.addComponent(jTextField_Mutuelle, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_8))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_jPanel1.createParallelGroup(Alignment.BASELINE)
								.addComponent(jTextField_Secu, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_9))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_jPanel1.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel)
								.addComponent(comboBox_genre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_jPanel1.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_10)
								.addComponent(comboBox_grpSanguin, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(34)
							.addGroup(gl_jPanel1.createParallelGroup(Alignment.BASELINE)
								.addComponent(jButton_Delete, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap())
		);
       
		frame.getContentPane().setLayout(gl_jPanel1);
        pack();
    }                     

    // afficher les données de ligne jtable dans jtextfields dans l'événement cliqué par la souris
    private void jTable_Display_UsersMouseClicked(java.awt.event.MouseEvent evt) {                                                  
        
        int i = jTable_Display_Patient.getSelectedRow();
        TableModel model = jTable_Display_Patient.getModel();
        jTextField_Id.setText(model.getValueAt(i,0).toString());
        jTextField_Name.setText(model.getValueAt(i,1).toString());
        jTextField_FirstName.setText(model.getValueAt(i,2).toString());
        jTextField_Email.setText(model.getValueAt(i,3).toString());
        jTextField_Phone.setText(model.getValueAt(i,4).toString());
        jTextField_Phone_fixe.setText(model.getValueAt(i,5).toString());
        jTextField_Address.setText(model.getValueAt(i,6).toString());
        jTextField_Mutuelle.setText(model.getValueAt(i,7).toString());
        jTextField_Secu.setText(model.getValueAt(i,8).toString());
        comboBox_genre.getModel().setSelectedItem(model.getValueAt(i,9).toString());
        comboBox_grpSanguin.getModel().setSelectedItem(model.getValueAt(i,10).toString());
    }                                                 
                                            
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SupprimerPatient window = new SupprimerPatient();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
        });
    } 
}

