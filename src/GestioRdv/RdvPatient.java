package GestioRdv;

import static javax.swing.JOptionPane.showMessageDialog;

import java.awt.BorderLayout;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.JLabel;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.RowFilter;
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
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.awt.Dimension;
import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.Toolkit;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import com.toedter.calendar.JDateChooser;

import Manager.GestioRdvManager;

import com.toedter.calendar.JCalendar;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class RdvPatient extends javax.swing.JFrame {
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable_Display_Doctor;
    private JLabel lblNewLabel_11;
	private JDialog frame;
	Toolkit toolkit = Toolkit.getDefaultToolkit();
	Dimension screenSize = toolkit.getScreenSize();
	private JPanel panel_1;
	private JTextField Id_Patient;
	private JTextField Name_Patient;
	private JLabel lblNewLabel_4;
	private JTextField Id_Medecin;
	private JTextField Name_Medecin;
	private JTable jTable_Display_Patient;
	private JTextField JtextField_Search_Patient;
	private JTextField JtextField_Search_Doctor;
	private JComboBox combo_heure_deb;
	private JComboBox combo_genre;
	private JComboBox combo_sanguin;
	private JTextField Adresse;
	private JDateChooser dateChooser;

    public RdvPatient() {
        initComponents();
        Show_Users_In_JTable();
        Show_Users_In_JTable_Doctor();
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
        
   // Obtenir une liste de patient de la base de données
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
   
   // Obtenir une liste des médecins de la base de données
   public ArrayList<Medecin> getDoctorList()
   {
       ArrayList<Medecin> DoctorList = new ArrayList<Medecin>();
       Connection connection = getConnection();
       
       String query = "SELECT * FROM  `medecin` ";
       Statement st;
       ResultSet rs;
       
       try {
           st = connection.createStatement();
           rs = st.executeQuery(query);
           Medecin userD;
           while(rs.next())
           {
               userD = new Medecin(rs.getInt("ID"),
            		   rs.getString("Medecin_name"),
            		   rs.getString("Medecin_firstname"),
            		   rs.getString("Medecin_email"),
            		   rs.getString("Medecin_phone"),
            		   rs.getString("Medecin_profession"));
               DoctorList.add(userD);
           }
       } catch (Exception e) {
           e.printStackTrace();
       }
       return DoctorList;
   }
   
   // Afficher les données dans JTable
   public void Show_Users_In_JTable()
   {
       ArrayList<Patient> list = getUsersList();
       DefaultTableModel model1 = (DefaultTableModel)jTable_Display_Patient.getModel();
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
           model1.addRow(row);
       }
    }
   
   // Afficher les données dans JTable
   public void Show_Users_In_JTable_Doctor()
   {
       ArrayList<Medecin> list = getDoctorList();
       DefaultTableModel model2 = (DefaultTableModel)jTable_Display_Doctor.getModel();
       Object[] row = new Object[6];
       for(int i = 0; i < list.size(); i++)
       {
           row[0] = list.get(i).getId();
           row[1] = list.get(i).getName();
           row[2] = list.get(i).getFirstName();
           row[3] = list.get(i).getEmail();
           row[4] = list.get(i).getPhone();
           row[5] = list.get(i).getProfession();
           model2.addRow(row);
       }
    }
                 
   // All Button and design Update
    private void initComponents() {
		frame = new JDialog();
		frame.setModalityType(ModalityType.APPLICATION_MODAL);
		frame.setResizable(false);
		frame.setType(Type.POPUP);
		frame.setBounds(100, 100, 1250, 600);
		frame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		int x = (screenSize.width - frame.getWidth()) / 2;
		int y = (screenSize.height - frame.getHeight()) / 2;
		frame.setLocation(x,y);
		
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_Display_Doctor = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(SystemColor.menu);

        jTable_Display_Doctor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Nom", "Prénom", "Email","Tél Mobile","Profession"
            }
        ));
        
        jTable_Display_Doctor.setAutoResizeMode(jTable_Display_Doctor.AUTO_RESIZE_OFF);
        
        jTable_Display_Doctor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_Display_UsersMouseClicked_Doctor(evt);
            }
        });
        jScrollPane1.setViewportView(jTable_Display_Doctor);
        
        lblNewLabel_11 = new JLabel("Ajouter un RDV");
        lblNewLabel_11.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_11.setFont(new Font("Tahoma", Font.PLAIN, 19));
		
		JPanel panel = new JPanel();
		
		panel.setBorder(javax.swing.BorderFactory.createTitledBorder("Informations du patient"));
		
		panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JScrollPane scrollPane = new JScrollPane();
		
		jTable_Display_Patient = new JTable();
		jTable_Display_Patient.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
					"Id", "Nom", "Prénom", "Email","Tél Mobile","Tél Fixe","Adresse","Mutuelle","Sécurité Sociale","Genre","Groupe Sanguin"
			}
		));
		jTable_Display_Patient.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        jTable_Display_Patient.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_Display_UsersMouseClicked(evt);
            }
        });
		scrollPane.setViewportView(jTable_Display_Patient);
		

		
		JLabel lblPatients = new JLabel("Patients");
		lblPatients.setHorizontalAlignment(SwingConstants.CENTER);
		lblPatients.setFont(new Font("Poor Richard", Font.PLAIN, 21));
		
		JLabel lblMdecins = new JLabel("M\u00E9decins");
		lblMdecins.setHorizontalAlignment(SwingConstants.CENTER);
		lblMdecins.setFont(new Font("Poor Richard", Font.PLAIN, 21));
		
		
		JLabel lblNewLabel = new JLabel("ID du patient");
		
		Id_Patient = new JTextField();
		Id_Patient.setEditable(false);
		
		JLabel lblNewLabel_1 = new JLabel("Nom du patient");
		
		Name_Patient = new JTextField();
		Name_Patient.setEditable(false);
		Name_Patient.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Genre");
		
		JLabel lblNewLabel_3 = new JLabel("Groupe Sanguin");
		
		lblNewLabel_4 = new JLabel("Date de RDV");
		lblNewLabel_4.setToolTipText("");
		
		JDateChooser dateChooser = new JDateChooser();
		
		/*String pattern  = "dd-MM-yyyy";
		DateFormat formatter = new SimpleDateFormat(pattern);
		
		String dateRDV = formatter.format(dateChooser);
		dateChooser.setDateFormatString("yyyy-MM-dd");*/

		JLabel lblNewLabel_5 = new JLabel("Heure");
		
		JLabel lblNewLabel_7 = new JLabel("ID du m\u00E9decin");
		
		Id_Medecin = new JTextField();
		Id_Medecin.setEditable(false);
		Id_Medecin.setColumns(10);
		
		JLabel lblNewLabel_8 = new JLabel("Nom du m\u00E9decin");
		
		Name_Medecin = new JTextField();
		Name_Medecin.setEditable(false);
		Name_Medecin.setColumns(10);
		
		
		
		combo_heure_deb = new JComboBox();
		combo_heure_deb.setModel(new DefaultComboBoxModel(new String[] {"09H00", "10H00", "11H00", "12H00", "13H00", "14H00", "15H00", "16H00", "17H00", "18H00"}));
		
		combo_genre = new JComboBox();
		combo_genre.setModel(new DefaultComboBoxModel(new String[] {"Homme", "Femme", "Autres"}));
		
		combo_sanguin = new JComboBox();
		combo_sanguin.setModel(new DefaultComboBoxModel(new String[] {"A-", "A+", "AB-", "AB+", "B-", "B+", "O-", "O+"}));
		
        Date date = new Date(System.currentTimeMillis());

	    dateChooser.setMinSelectableDate(date);
	    
		JButton btn_Ajouter = new JButton("Ajouter");
		btn_Ajouter.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				//formatage de dateChooser
				if(dateChooser.getDate() != null) {

					 SimpleDateFormat tdate = new SimpleDateFormat("dd-MM-yyyy");
				     String dateRDV = tdate.format(dateChooser.getDate());

				     
					 String dateNow = tdate.format(date);
					 
			           if(dateRDV.compareTo(dateNow)==0){
							showMessageDialog(null, "Vous ne pouvez pas prendre de RDV aujourd'hui.");
			           }
			           else {
				        	  
			   			GestioRdvManager.AjoutRDV(Id_Patient.getText(), Id_Medecin.getText(),dateRDV,(String) combo_heure_deb.getSelectedItem(), Adresse.getText());
			               //frame.dispose();
			   			}
				} else {
	            	showMessageDialog(null, "Un des champs est vide !");
				}



			}});
		
		JButton btn_reset = new JButton("R\u00E9initialiser");
		btn_reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Id_Patient.setText("");	
				Name_Patient.setText("");	
				combo_genre.setSelectedItem(-1);	
				combo_sanguin.setSelectedItem(-1);	
				dateChooser.setDate(null);	
				combo_heure_deb.setSelectedItem(-1);
				Id_Medecin.setText("");	
				Name_Medecin.setText("");	
				Adresse.setText("");
			}
		});
		
		JButton btn_retour = new JButton("Accueil");
		btn_retour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btn_retour.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
			}
		});
		
		JButton btnDispoRdv = new JButton("Disponibilit\u00E9 RDV");
		btnDispoRdv.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				GestioRdv.DispoRdv DispoRdv = new GestioRdv.DispoRdv();
				DispoRdv.main(null);
				frame.dispose();
			}
		});
		
		
		JtextField_Search_Patient = new JTextField();
		JtextField_Search_Patient.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {	
		DefaultTableModel model1 = (DefaultTableModel)jTable_Display_Patient.getModel();
		String search = JtextField_Search_Patient.getText();
		TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(model1);
		jTable_Display_Patient.setRowSorter(tr);
		tr.setRowFilter(RowFilter.regexFilter(search));
			}
		});
		JtextField_Search_Patient.setColumns(10);
		
		JLabel lblNewLabel_10 = new JLabel("Recherche");
		
		JLabel label = new JLabel("Recherche");
		
		JtextField_Search_Doctor = new JTextField();
		JtextField_Search_Doctor.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				DefaultTableModel model2 = (DefaultTableModel)jTable_Display_Doctor.getModel();
				String search = JtextField_Search_Doctor.getText().toLowerCase();
				TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(model2);
				jTable_Display_Doctor.setRowSorter(tr);
				tr.setRowFilter(RowFilter.regexFilter(search));
			}
		});
		JtextField_Search_Doctor.setColumns(10);
		
		GroupLayout gl_jPanel1 = new GroupLayout(frame.getContentPane());
		gl_jPanel1.setHorizontalGroup(
			gl_jPanel1.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_jPanel1.createSequentialGroup()
					.addGap(91)
					.addComponent(lblNewLabel_11, GroupLayout.PREFERRED_SIZE, 208, GroupLayout.PREFERRED_SIZE)
					.addGap(255)
					.addComponent(lblPatients, GroupLayout.PREFERRED_SIZE, 208, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 201, Short.MAX_VALUE)
					.addComponent(lblMdecins, GroupLayout.PREFERRED_SIZE, 208, GroupLayout.PREFERRED_SIZE)
					.addGap(73))
				.addGroup(gl_jPanel1.createSequentialGroup()
					.addGap(11)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 321, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
					.addGroup(gl_jPanel1.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_jPanel1.createSequentialGroup()
							.addGap(137)
							.addComponent(lblNewLabel_10)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(JtextField_Search_Patient, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_jPanel1.createSequentialGroup()
							.addGap(18)
							.addComponent(scrollPane, 0, 0, Short.MAX_VALUE)))
					.addGroup(gl_jPanel1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_jPanel1.createSequentialGroup()
							.addGap(149)
							.addComponent(label)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(JtextField_Search_Doctor, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_jPanel1.createSequentialGroup()
							.addGap(18)
							.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 347, GroupLayout.PREFERRED_SIZE)))
					.addGap(47))
		);
		gl_jPanel1.setVerticalGroup(
			gl_jPanel1.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_jPanel1.createSequentialGroup()
					.addGroup(gl_jPanel1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_jPanel1.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_jPanel1.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_jPanel1.createParallelGroup(Alignment.BASELINE)
									.addComponent(lblNewLabel_11, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
									.addComponent(lblPatients, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE))
								.addComponent(lblMdecins, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_jPanel1.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_jPanel1.createSequentialGroup()
									.addGroup(gl_jPanel1.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_jPanel1.createParallelGroup(Alignment.BASELINE)
											.addComponent(lblNewLabel_10)
											.addComponent(JtextField_Search_Patient, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_jPanel1.createParallelGroup(Alignment.BASELINE)
											.addComponent(label)
											.addComponent(JtextField_Search_Doctor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_jPanel1.createParallelGroup(Alignment.BASELINE)
										.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 455, GroupLayout.PREFERRED_SIZE)
										.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 455, GroupLayout.PREFERRED_SIZE)))
								.addComponent(panel, GroupLayout.PREFERRED_SIZE, 441, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_jPanel1.createSequentialGroup()
							.addGap(97)
							.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 146, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(27, Short.MAX_VALUE))
		);
		

		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(9)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addComponent(btn_reset, GroupLayout.PREFERRED_SIZE, 118, Short.MAX_VALUE)
								.addComponent(btn_Ajouter, GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnDispoRdv, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addContainerGap()
							.addComponent(btn_retour, GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)))
					.addContainerGap())
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(btn_Ajouter)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btn_reset)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnDispoRdv)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btn_retour)
					.addContainerGap(21, Short.MAX_VALUE))
		);
		panel_1.setLayout(gl_panel_1);
		
		Adresse = new JTextField();
		Adresse.setColumns(10);
		
		JLabel lblAdresse = new JLabel("Adresse");
		lblAdresse.setToolTipText("");
		
		
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblNewLabel_8)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(Name_Medecin))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblNewLabel_7)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(Id_Medecin))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblNewLabel_5)
							.addGap(31)
							.addComponent(combo_heure_deb, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
							.addComponent(lblNewLabel_4)
							.addGap(42)
							.addComponent(dateChooser, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
							.addComponent(lblAdresse, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(Adresse, GroupLayout.PREFERRED_SIZE, 168, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel_1)
								.addComponent(lblNewLabel_2)
								.addComponent(lblNewLabel_3)
								.addComponent(lblNewLabel))
							.addGap(30)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
								.addComponent(combo_sanguin, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(combo_genre, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(Name_Patient)
								.addComponent(Id_Patient, GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE))))
					.addGap(9))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(23)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(Id_Patient, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(Name_Patient, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_2)
						.addComponent(combo_genre, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_3)
						.addComponent(combo_sanguin, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(Adresse, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblAdresse))
					.addGap(12)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblNewLabel_4)
						.addComponent(dateChooser, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(combo_heure_deb, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_5))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_7)
						.addComponent(Id_Medecin))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_8)
						.addComponent(Name_Medecin, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(263))
		);
		panel.setLayout(gl_panel);
       
		frame.getContentPane().setLayout(gl_jPanel1);
        pack();
    }                     

    // afficher les données de ligne jtable dans jtextfields dans l'événement cliqué par la souris
    private void jTable_Display_UsersMouseClicked(java.awt.event.MouseEvent evt) {                                                  
        
        int i = jTable_Display_Patient.getSelectedRow();
        
        TableModel model1 = jTable_Display_Patient.getModel();
        Id_Patient.setText(model1.getValueAt(i,0).toString());
        Name_Patient.setText(model1.getValueAt(i,1).toString());
		combo_genre.getModel().setSelectedItem(model1.getValueAt(i,9).toString());
        combo_sanguin.getModel().setSelectedItem(model1.getValueAt(i,10).toString());
        
    }        
    
    
    private void jTable_Display_UsersMouseClicked_Doctor(java.awt.event.MouseEvent evt) {                                                  
        
        int j = jTable_Display_Doctor.getSelectedRow();
        TableModel model2 = jTable_Display_Doctor.getModel();
        Id_Medecin.setText(model2.getValueAt(j,0).toString());
        Name_Medecin.setText(model2.getValueAt(j,1).toString());
    }
                                            
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RdvPatient window = new RdvPatient();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
        });
    } 
}

