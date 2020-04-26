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

public class ModifierSalarie extends javax.swing.JFrame {
	
    private javax.swing.JButton btnUpdate;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable_Display_Salarie;
    private JLabel lblRole;
    private JComboBox comboBox_role;
    private JTextField jTextField_Id;
    private JTextField jTextField_Name;
    private JTextField jTextField_FirstName;
    private JTextField jTextField_Email;
    private JLabel lblMdp;
    private JLabel lblTelephone;
    private JTextField jTextField_Phone;
    private JLabel lblModifierUnSalarie;
	private JDialog frame;
	Toolkit toolkit = Toolkit.getDefaultToolkit();
	Dimension screenSize = toolkit.getScreenSize();
	private JPasswordField jTextField_Password;
	private JLabel lblNewLabel;
	private JTextField JtextField_Search;

    public ModifierSalarie() {
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
   public ArrayList<Salarie> getUsersList()
   {
       ArrayList<Salarie> usersList = new ArrayList<Salarie>();
       Connection connection = getConnection();
       
       String query = "SELECT * FROM  `register` ";
       Statement st;
       ResultSet rs;
       
       try {
           st = connection.createStatement();
           rs = st.executeQuery(query);
           Salarie user;
           while(rs.next())
           {
               user = new Salarie(rs.getInt("ID"),
            		   rs.getString("Name"),
            		   rs.getString("Firstname"),
            		   rs.getString("Email"),
            		   rs.getString("Password"),
            		   rs.getString("Phone"),
            		   rs.getString("Role"));
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
       ArrayList<Salarie> list = getUsersList();
       DefaultTableModel model = (DefaultTableModel)jTable_Display_Salarie.getModel();
       Object[] row = new Object[7];
       for(int i = 0; i < list.size(); i++)
       {
           row[0] = list.get(i).getId();
           row[1] = list.get(i).getName();
           row[2] = list.get(i).getFirstName();
           row[3] = list.get(i).getEmail();
           row[4] = list.get(i).getPassword();
           row[5] = list.get(i).getPhone();
           row[6] = list.get(i).getRole();
           model.addRow(row);
       }
    }
                 
   // All Button and design Update
    private void initComponents() {
		frame = new JDialog();
		frame.setModalityType(ModalityType.APPLICATION_MODAL);
		frame.setResizable(false);
		frame.setType(Type.POPUP);
		frame.setBounds(100, 100, 950, 625);
		frame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		int x = (screenSize.width - frame.getWidth()) / 2;
		int y = (screenSize.height - frame.getHeight()) / 2;
		frame.setLocation(x,y);
		
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_Display_Salarie = new javax.swing.JTable();
       
        //Btn Modifier
        btnUpdate = new javax.swing.JButton();
        btnUpdate.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent arg0) {
				String email = jTextField_Email.getText();
				String regexStr = "^[0-9]*$";
        		if (jTextField_Phone.getText().trim().matches(regexStr) && jTextField_Phone.getText().length() == 10) {
					 if (email.contains("@")) 
						 Manager.AdminManager.ModifPersonnel(jTextField_Id.getText(), jTextField_Name.getText(), 
        				 jTextField_FirstName.getText(), jTextField_Email.getText(), 
        				 jTextField_Password.getText(), jTextField_Phone.getText(),
        				 (String) comboBox_role.getSelectedItem());
					 else
						 showMessageDialog(null, "Veuillez saisir un mail valide ! Par exemple : test@mail.fr");
					 }
        			else {
        				showMessageDialog(null, "Le numéro de portable doit contenir 10 chiffres !");
        			 }
        				DefaultTableModel model = (DefaultTableModel)jTable_Display_Salarie.getModel();
        				model.setRowCount(0);
       				 	Show_Users_In_JTable();
        	}});
        btnUpdate.setText("Modifier");
       

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(SystemColor.menu);

        jTable_Display_Salarie.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Nom", "Prénom", "Email","Mot de passe","Tél","Role"
            }
        ));
        
        jTable_Display_Salarie.setAutoResizeMode(jTable_Display_Salarie.AUTO_RESIZE_OFF);
        
        jTable_Display_Salarie.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_Display_UsersMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable_Display_Salarie);

        lblRole = new JLabel("Role:");
        lblRole.setFont(new Font("Tahoma", Font.PLAIN, 18));
        
        JLabel lblid = new JLabel("ID:");
        lblid.setFont(new Font("Tahoma", Font.PLAIN, 18));
        
        jTextField_Id = new JTextField();
        jTextField_Id.setColumns(10);
        jTextField_Id.setEditable(false);

        
        JLabel lblNom = new JLabel("Nom:");
        lblNom.setFont(new Font("Tahoma", Font.PLAIN, 18));
        
        jTextField_Name = new JTextField();
        jTextField_Name.setColumns(10);
        
        jTextField_FirstName = new JTextField();
        jTextField_FirstName.setColumns(10);
        
        JLabel lblPrenom = new JLabel("Pr\u00E9nom:");
        lblPrenom.setFont(new Font("Tahoma", Font.PLAIN, 18));
        
        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 18));
        
        jTextField_Email = new JTextField();
        jTextField_Email.setColumns(10);
        
        lblMdp = new JLabel("Mot de passe:");
        lblMdp.setFont(new Font("Tahoma", Font.PLAIN, 18));
        
        lblTelephone = new JLabel("T\u00E9l\u00E9phone:");
        lblTelephone.setFont(new Font("Tahoma", Font.PLAIN, 18));
        
        jTextField_Phone = new JTextField();
        jTextField_Phone.setColumns(10);
        
        comboBox_role = new JComboBox();
        comboBox_role.setModel(new DefaultComboBoxModel(new String[] {"ADMIN", "GESTIOSTOCK", "GESTIORDV"}));
        
        lblModifierUnSalarie = new JLabel("Modifier un salari\u00E9");
        lblModifierUnSalarie.setHorizontalAlignment(SwingConstants.CENTER);
        lblModifierUnSalarie.setFont(new Font("Tahoma", Font.PLAIN, 19));
		
		JButton btnRetour = new JButton("Retour");
		btnRetour.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
			}
		});
		
		jTextField_Password = new JPasswordField();
		
		lblNewLabel = new JLabel("Recherche");
		
		JtextField_Search = new JTextField();
		JtextField_Search.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				DefaultTableModel model = (DefaultTableModel)jTable_Display_Salarie.getModel();
				String search = JtextField_Search.getText();
				TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(model);
				jTable_Display_Salarie.setRowSorter(tr);
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
								.addGroup(gl_jPanel1.createSequentialGroup()
									.addGroup(gl_jPanel1.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_jPanel1.createSequentialGroup()
											.addGroup(gl_jPanel1.createParallelGroup(Alignment.LEADING)
												.addComponent(lblTelephone)
												.addComponent(lblNom)
												.addComponent(lblMdp)
												.addComponent(lblEmail)
												.addComponent(lblPrenom)
												.addComponent(lblid))
											.addGap(35)
											.addGroup(gl_jPanel1.createParallelGroup(Alignment.LEADING, false)
												.addComponent(jTextField_Password)
												.addComponent(jTextField_Email)
												.addComponent(jTextField_Phone, Alignment.TRAILING)
												.addComponent(jTextField_Id, GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE)
												.addComponent(jTextField_Name)
												.addComponent(jTextField_FirstName)
												.addComponent(comboBox_role, 0, 223, Short.MAX_VALUE)))
										.addComponent(lblRole))
									.addPreferredGap(ComponentPlacement.RELATED, 37, Short.MAX_VALUE))
								.addGroup(Alignment.TRAILING, gl_jPanel1.createSequentialGroup()
									.addComponent(btnRetour)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(btnUpdate)
									.addGap(36)))
							.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 510, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(gl_jPanel1.createSequentialGroup()
							.addComponent(lblModifierUnSalarie, GroupLayout.PREFERRED_SIZE, 326, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 402, Short.MAX_VALUE)
							.addComponent(lblNewLabel)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(JtextField_Search, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(52))))
		);
		gl_jPanel1.setVerticalGroup(
			gl_jPanel1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_jPanel1.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblModifierUnSalarie, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
					.addGap(33)
					.addGroup(gl_jPanel1.createParallelGroup(Alignment.BASELINE)
						.addComponent(jTextField_Id, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblid))
					.addGap(11)
					.addGroup(gl_jPanel1.createParallelGroup(Alignment.BASELINE)
						.addComponent(jTextField_Name, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNom))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_jPanel1.createParallelGroup(Alignment.BASELINE)
						.addComponent(jTextField_FirstName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblPrenom))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_jPanel1.createParallelGroup(Alignment.BASELINE)
						.addComponent(jTextField_Email, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblEmail))
					.addGap(9)
					.addGroup(gl_jPanel1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblMdp)
						.addComponent(jTextField_Password, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(12)
					.addGroup(gl_jPanel1.createParallelGroup(Alignment.BASELINE)
						.addComponent(jTextField_Phone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblTelephone))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_jPanel1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblRole)
						.addComponent(comboBox_role, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_jPanel1.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnUpdate, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnRetour, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(224, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, gl_jPanel1.createSequentialGroup()
					.addContainerGap(48, Short.MAX_VALUE)
					.addGroup(gl_jPanel1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(JtextField_Search, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 512, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
       
		frame.getContentPane().setLayout(gl_jPanel1);
        pack();
    }                     

    // afficher les données de ligne jtable dans jtextfields dans l'événement cliqué par la souris
    private void jTable_Display_UsersMouseClicked(java.awt.event.MouseEvent evt) {                                                  
        
        int i = jTable_Display_Salarie.getSelectedRow();
        TableModel model = jTable_Display_Salarie.getModel();
        jTextField_Id.setText(model.getValueAt(i,0).toString());
        jTextField_Name.setText(model.getValueAt(i,1).toString());
        jTextField_FirstName.setText(model.getValueAt(i,2).toString());
        jTextField_Email.setText(model.getValueAt(i,3).toString());
        jTextField_Password.setText(model.getValueAt(i,4).toString());
        jTextField_Phone.setText(model.getValueAt(i,5).toString());
        comboBox_role.getModel().setSelectedItem(model.getValueAt(i,6).toString());
    }                                                 
                                            
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ModifierSalarie window = new ModifierSalarie();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
        });
    } 
}

