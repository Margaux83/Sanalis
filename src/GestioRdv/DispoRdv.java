package GestioRdv;

import static javax.swing.JOptionPane.showMessageDialog;

import java.awt.BorderLayout;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import net.proteanit.sql.DbUtils;

import com.mysql.jdbc.PreparedStatement;
import com.toedter.calendar.JCalendar;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class DispoRdv extends javax.swing.JFrame {
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable_Display_Doctor;
    private JLabel lblNewLabel_11;
	private JDialog frame;
	Toolkit toolkit = Toolkit.getDefaultToolkit();
	Dimension screenSize = toolkit.getScreenSize();
	private JPanel panel_1;
	private JLabel lblNewLabel_4;
	private JTextField Id_Medecin;
	private JTextField Name_Medecin;
	private JTable jTable_Display_Patient;
	private JTextField JtextField_Search_Doctor;
	private JDateChooser dateChooser;

    public DispoRdv() {
        initComponents();
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
        
   // Obtenir une liste d'utilisateurs de la base de données

   
   // Obtenir une liste des médecin de la base de données
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
		frame.setBounds(100, 100, 1200, 625);
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
        
        lblNewLabel_11 = new JLabel("Disponibilit\u00E9 des m\u00E9decins");
        lblNewLabel_11.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_11.setFont(new Font("Tahoma", Font.PLAIN, 19));
		
		JPanel panel = new JPanel();
		
		panel.setBorder(javax.swing.BorderFactory.createTitledBorder("Informations"));
		
		panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JScrollPane scrollPane = new JScrollPane();
		
		jTable_Display_Patient = new JTable();
		jTable_Display_Patient.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
					"Id", "Id_medecin", "Medecin", "Email","Date_RDV","Begin_hour"
			}
		));
		jTable_Display_Patient.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        jTable_Display_Patient.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_Display_UsersMouseClicked(evt);
            }
        });
		scrollPane.setViewportView(jTable_Display_Patient);
		

		
		JLabel lblPatients = new JLabel("RDV");
		lblPatients.setHorizontalAlignment(SwingConstants.CENTER);
		lblPatients.setFont(new Font("Poor Richard", Font.PLAIN, 21));
		
		JLabel lblMdecins = new JLabel("M\u00E9decins");
		lblMdecins.setHorizontalAlignment(SwingConstants.CENTER);
		lblMdecins.setFont(new Font("Poor Richard", Font.PLAIN, 21));
		
		lblNewLabel_4 = new JLabel("Date de RDV");
		lblNewLabel_4.setToolTipText("");
		
		JDateChooser dateChooser = new JDateChooser();;
		
		JLabel lblNewLabel_7 = new JLabel("ID du m\u00E9decin");
		
		Id_Medecin = new JTextField();
		Id_Medecin.setEditable(false);
		Id_Medecin.setColumns(10);
		
		JLabel lblNewLabel_8 = new JLabel("Nom du m\u00E9decin");
		
		Name_Medecin = new JTextField();
		Name_Medecin.setEditable(false);
		Name_Medecin.setColumns(10);
		

        
		JButton btn_Ajouter = new JButton("Valider");
		btn_Ajouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 Connection cn = getConnection();
			     Statement st;
			     
				 try {
			     st = cn.createStatement();

				//formatage de dateChooser
				 String dob=""+dateChooser.getDate();
				 SimpleDateFormat tdate = new SimpleDateFormat("dd-MM-yyyy");
				 String dateRDV = tdate.format(dateChooser.getDate());
				
		            
				 String query = "Select Id_patient, Id_medecin, Date_RDV, Begin_hour, Address_RDV from rdv inner join medecin on medecin.ID = rdv.Id_medecin WHERE Date_Rdv = '" + dateRDV + "' AND medecin.ID = '" + Id_Medecin.getText() + "'";
				    
				 ResultSet rs = st.executeQuery(query);

				 if(rs.next()) {
					 jTable_Display_Patient.setModel(DbUtils.resultSetToTableModel(rs));
				 }
				 else {
		                showMessageDialog(null, "Le docteur est disponible toute la journée :)");				 }
		            }catch (Exception e) {
		           e.printStackTrace();

			}
				 }});
		
	
		
		JButton btn_reset = new JButton("R\u00E9initialiser");
		btn_reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dateChooser.setDate(null);	
				Id_Medecin.setText("");	
				Name_Medecin.setText("");	
			}
		});
		
		JButton btn_retour = new JButton("Accueil");
		btn_retour.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
			}
		});
		
		JButton btnRdvPatient = new JButton("Prise de RDV");
		btnRdvPatient.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				GestioRdv.RdvPatient RdvPatient = new GestioRdv.RdvPatient();
				RdvPatient.main(null);
				frame.dispose();

			}
		});
		
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
			gl_jPanel1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_jPanel1.createSequentialGroup()
					.addGroup(gl_jPanel1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_jPanel1.createSequentialGroup()
							.addGap(60)
							.addComponent(lblNewLabel_11, GroupLayout.PREFERRED_SIZE, 239, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_jPanel1.createSequentialGroup()
							.addGap(20)
							.addGroup(gl_jPanel1.createParallelGroup(Alignment.LEADING)
								.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(panel, GroupLayout.PREFERRED_SIZE, 355, GroupLayout.PREFERRED_SIZE))))
					.addGroup(gl_jPanel1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_jPanel1.createSequentialGroup()
							.addGap(67)
							.addComponent(lblPatients, GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED, 227, Short.MAX_VALUE)
							.addGroup(gl_jPanel1.createParallelGroup(Alignment.LEADING)
								.addGroup(Alignment.TRAILING, gl_jPanel1.createSequentialGroup()
									.addComponent(label)
									.addGap(18)
									.addComponent(JtextField_Search_Doctor, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE)
									.addGap(19))
								.addGroup(Alignment.TRAILING, gl_jPanel1.createSequentialGroup()
									.addComponent(lblMdecins, GroupLayout.PREFERRED_SIZE, 208, GroupLayout.PREFERRED_SIZE)
									.addGap(48))))
						.addGroup(Alignment.TRAILING, gl_jPanel1.createSequentialGroup()
							.addGap(18)
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 343, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())))
		);
		gl_jPanel1.setVerticalGroup(
			gl_jPanel1.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_jPanel1.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel_11, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_jPanel1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_jPanel1.createSequentialGroup()
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 157, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 148, GroupLayout.PREFERRED_SIZE))
						.addGroup(Alignment.TRAILING, gl_jPanel1.createSequentialGroup()
							.addGroup(gl_jPanel1.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblPatients, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
								.addComponent(JtextField_Search_Doctor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(label))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_jPanel1.createParallelGroup(Alignment.BASELINE)
								.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 473, GroupLayout.PREFERRED_SIZE)
								.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 470, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap())
				.addGroup(Alignment.LEADING, gl_jPanel1.createSequentialGroup()
					.addGap(27)
					.addComponent(lblMdecins, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(523, Short.MAX_VALUE))
		);
		

		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(6)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(btnRdvPatient, GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
						.addComponent(btn_reset, GroupLayout.PREFERRED_SIZE, 97, Short.MAX_VALUE)
						.addComponent(btn_Ajouter, GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE)
						.addComponent(btn_retour, GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE))
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
					.addComponent(btnRdvPatient)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btn_retour)
					.addContainerGap(46, Short.MAX_VALUE))
		);
		panel_1.setLayout(gl_panel_1);
		
		
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel_4)
								.addComponent(lblNewLabel_7))
							.addGap(18)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(dateChooser, GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE)
								.addComponent(Id_Medecin, 216, 216, 216)))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblNewLabel_8)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(Name_Medecin, GroupLayout.PREFERRED_SIZE, 210, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_4)
						.addComponent(dateChooser, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_7)
						.addComponent(Id_Medecin, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_8)
						.addComponent(Name_Medecin, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(399, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
       
		frame.getContentPane().setLayout(gl_jPanel1);
        pack();
    }                     

    // afficher les données de ligne jtable dans jtextfields dans l'événement cliqué par la souris
    private void jTable_Display_UsersMouseClicked(java.awt.event.MouseEvent evt) {                                                  
        
        int i = jTable_Display_Patient.getSelectedRow();
        
        TableModel model1 = jTable_Display_Patient.getModel();
        
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
					DispoRdv window = new DispoRdv();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
        });
    } 
}

