package GestioStock;

import static javax.swing.JOptionPane.showMessageDialog;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

import com.toedter.calendar.JDateChooser;

public class ModifierMedoc extends javax.swing.JFrame {
	
    private javax.swing.JButton jButton_Update;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable_Display_Medoc;
    private JLabel lblNewLabel;
    private JComboBox comboBox_Toxicity;
    private JTextField jTextField_Id;
    private JTextField jTextField_Libelle;
    private JTextField jTextField_Med_Code;
    private JTextField jTextField_Name;
    private JLabel lblNewLabel_5;
    private JLabel lblNewLabel_6;
    private JTextField jTextField_Laboratory;
    private JLabel lblNewLabel_7;
    private JTextField jTextField_Description;
    private JTextField jTextField_Quantity;
    private JTextField jTextField_Quantity_type;
    private JLabel lblNewLabel_11;
	private JDialog frame;
	Toolkit toolkit = Toolkit.getDefaultToolkit();
	Dimension screenSize = toolkit.getScreenSize();
	private JTextField jTextField_Med_type;
	private JTextField jTextField_Med_use;
	private JTextField jTextField_Price;
	private JLabel lblNewLabel_14;
	private JTextField jTextField_Quantity_stock;
	private JLabel lblNewLabel_15;
	private JTextField JtextField_Search;

    public ModifierMedoc() {
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
   public ArrayList<Medoc> getUsersList()
   {
       ArrayList<Medoc> usersList = new ArrayList<Medoc>();
       Connection connection = getConnection();
       
       String query = "SELECT * FROM  `medicament` ";
       Statement st;
       ResultSet rs;
       
       try {
           st = connection.createStatement();
           rs = st.executeQuery(query);
           Medoc user;
           while(rs.next())
           {
               user = new Medoc(rs.getInt("ID"),
            		   rs.getInt("Libelle"),
            		   rs.getString("Med_code"),
            		   rs.getString("Name"),
            		   rs.getString("Expi_date"),
            		   rs.getString("Laboratory"),
            		   rs.getString("Description"),
            		   rs.getInt("Quantity"),
            		   rs.getString("Quantity_type"),
            		   rs.getString("Med_type"),
            		   rs.getString("Med_use"),
            		   rs.getInt("Price"),
            		   rs.getString("Toxicity"),
            		   rs.getInt("Quantity_stock"));
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
       ArrayList<Medoc> list = getUsersList();
       DefaultTableModel model = (DefaultTableModel)jTable_Display_Medoc.getModel();
       Object[] row = new Object[14];
       for(int i = 0; i < list.size(); i++)
       {
           row[0] = list.get(i).getId();
           row[1] = list.get(i).getLibelle();
           row[2] = list.get(i).getMed_code();
           row[3] = list.get(i).getName();
           row[4] = list.get(i).getExpi_date();
           row[5] = list.get(i).getLaboratory();
           row[6] = list.get(i).getDescription();
           row[7] = list.get(i).getQuantity();
           row[8] = list.get(i).getQuantity_type();
           row[9] = list.get(i).getMed_type();
           row[10] = list.get(i).getMed_use();
           row[11] = list.get(i).getPrice();
           row[12] = list.get(i).getToxicity();
           row[13] = list.get(i).getQuantity_stock();
           model.addRow(row);
       }
    }
                 
   // All Button and design Update
    private void initComponents() {
		frame = new JDialog();
		frame.setModalityType(ModalityType.APPLICATION_MODAL);
		frame.setResizable(false);
		frame.setType(Type.POPUP);
		frame.setBounds(100, 100, 900, 647);
		frame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		int x = (screenSize.width - frame.getWidth()) / 2;
		int y = (screenSize.height - frame.getHeight()) / 2;
		frame.setLocation(x,y);
		
        jPanel1 = new javax.swing.JPanel();		
        JDateChooser JdateChooser =  new JDateChooser();

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_Display_Medoc = new javax.swing.JTable();
        jTable_Display_Medoc.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		
        		int i = jTable_Display_Medoc.getSelectedRow();
                jTextField_Id.setText(jTable_Display_Medoc.getValueAt(i,0).toString());
                jTextField_Id.setText(jTable_Display_Medoc.getValueAt(i,0).toString());
                jTextField_Libelle.setText(jTable_Display_Medoc.getValueAt(i,1).toString());
                jTextField_Med_Code.setText(jTable_Display_Medoc.getValueAt(i,2).toString());
                jTextField_Name.setText(jTable_Display_Medoc.getValueAt(i,3).toString());
                jTextField_Laboratory.setText(jTable_Display_Medoc.getValueAt(i,5).toString());
                jTextField_Description.setText(jTable_Display_Medoc.getValueAt(i,6).toString());
                jTextField_Quantity.setText(jTable_Display_Medoc.getValueAt(i,7).toString());
                jTextField_Quantity_type.setText(jTable_Display_Medoc.getValueAt(i,8).toString());
                jTextField_Med_type.setText(jTable_Display_Medoc.getValueAt(i,9).toString());
                jTextField_Med_use.setText(jTable_Display_Medoc.getValueAt(i,10).toString());
                jTextField_Price.setText(jTable_Display_Medoc.getValueAt(i,11).toString());
                comboBox_Toxicity.getModel().setSelectedItem(jTable_Display_Medoc.getValueAt(i,12).toString());
                jTextField_Quantity_stock.setText(jTable_Display_Medoc.getValueAt(i,13).toString()); 
                
                String fecha = jTable_Display_Medoc.getValueAt(i, 4).toString();
                SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd-MM-yyyy");
                Date fechaN = null;
                
                try {
                    fechaN = formatoDelTexto.parse(fecha);
                    JdateChooser.setDate(fechaN);
                } catch (ParseException ex) {
                    ex.printStackTrace();
                }
            }    ;
        });
       

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(SystemColor.menu);

        jTable_Display_Medoc.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Libellé médicament", "Code de médicament", "Nom générique","Date de péremption","Laboratoire","Description","Quantité","mg, g, ml","Type de solution","Indication d'utilisation","Prix","Toxicité","Quantité de stock"
            }
        ));
        
        jTable_Display_Medoc.setAutoResizeMode(jTable_Display_Medoc.AUTO_RESIZE_OFF);
        
        jScrollPane1.setViewportView(jTable_Display_Medoc);

        lblNewLabel = new JLabel("Type de solution:");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
        
        
        JLabel lblNewLabel_1 = new JLabel("ID:");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
        
        jTextField_Id = new JTextField();
        jTextField_Id.setColumns(10);
        jTextField_Id.setEditable(false);

        
        JLabel lblNewLabel_2 = new JLabel("Libell\u00E9:");
        lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 17));
        
        jTextField_Libelle = new JTextField();
        jTextField_Libelle.setColumns(10);
        
        jTextField_Med_Code = new JTextField();
        jTextField_Med_Code.setColumns(10);
        
        JLabel lblNewLabel_3 = new JLabel("Code de m\u00E9dicament:");
        lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 17));
        
        JLabel lblNewLabel_4 = new JLabel("Nom g\u00E9n\u00E9rique:");
        lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 17));
        
        jTextField_Name = new JTextField();
        jTextField_Name.setColumns(10);
        
        lblNewLabel_5 = new JLabel("Date de p\u00E9remption:");
        lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 17));
        
        lblNewLabel_6 = new JLabel("Laboratoire:");
        lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 17));
        
        jTextField_Laboratory = new JTextField();
        jTextField_Laboratory.setColumns(10);
        
        lblNewLabel_7 = new JLabel("Description");
        lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 17));
        
        jTextField_Description = new JTextField();
        jTextField_Description.setColumns(10);
        
        JLabel lblNewLabel_8 = new JLabel("Quantit\u00E9:");
        lblNewLabel_8.setFont(new Font("Tahoma", Font.PLAIN, 17));
        
        jTextField_Quantity = new JTextField();
        jTextField_Quantity.setColumns(10);
        
        JLabel lblNewLabel_9 = new JLabel("mg, g, ml:");
        lblNewLabel_9.setFont(new Font("Tahoma", Font.PLAIN, 17));
        
        jTextField_Quantity_type = new JTextField();
        jTextField_Quantity_type.setColumns(10);
        
        JLabel lblNewLabel_10 = new JLabel("Toxicit\u00E9:");
        lblNewLabel_10.setFont(new Font("Tahoma", Font.PLAIN, 17));
        
        comboBox_Toxicity = new JComboBox();
        comboBox_Toxicity.setModel(new DefaultComboBoxModel(new String[] {"Niveaux 1", "Niveaux 2", "Niveaux 3"}));
        
        lblNewLabel_11 = new JLabel("Modifier un m\u00E9dicament");
        lblNewLabel_11.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_11.setFont(new Font("Tahoma", Font.PLAIN, 19));
		
		JButton btnNewButton = new JButton("Retour");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
			}
		});
		
		jTextField_Med_type = new JTextField();
		jTextField_Med_type.setColumns(10);
		
		JLabel lblNewLabel_12 = new JLabel("Indication d'utilisation:");
		lblNewLabel_12.setFont(new Font("Tahoma", Font.PLAIN, 17));
		
		jTextField_Med_use = new JTextField();
		jTextField_Med_use.setColumns(10);
		
		JLabel lblNewLabel_13 = new JLabel("Prix");
		lblNewLabel_13.setFont(new Font("Tahoma", Font.PLAIN, 17));
		
		jTextField_Price = new JTextField();
		jTextField_Price.setColumns(10);
		
		lblNewLabel_14 = new JLabel("Quantit\u00E9 de stock:");
		lblNewLabel_14.setFont(new Font("Tahoma", Font.PLAIN, 17));
		
		jTextField_Quantity_stock = new JTextField();
		jTextField_Quantity_stock.setEditable(false);
		jTextField_Quantity_stock.setColumns(10);
	
		
		lblNewLabel_15 = new JLabel("Recherche");
		
		JtextField_Search = new JTextField();
		JtextField_Search.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				DefaultTableModel model = (DefaultTableModel)jTable_Display_Medoc.getModel();
				String search = JtextField_Search.getText();
				TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(model);
				jTable_Display_Medoc.setRowSorter(tr);
				tr.setRowFilter(RowFilter.regexFilter(search));
			}
		});
		JtextField_Search.setColumns(10);
		
        Date date = new Date(System.currentTimeMillis());

        JdateChooser.setMinSelectableDate(date);
	    
		 //Update Button 
        jButton_Update = new javax.swing.JButton();
        jButton_Update.setText("Modifier");
        jButton_Update.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
				//formatage de dateChooser
				 SimpleDateFormat tdate = new SimpleDateFormat("dd-MM-yyyy");
		         String dateMedoc = tdate.format(JdateChooser.getDate());
				 
		         Date date1 = new Date(System.currentTimeMillis());
				 String dateNow = tdate.format(date1);
        		
				try {
					int nombre = Integer.parseInt(jTextField_Price.getText());

				} catch(NumberFormatException e1) {
					showMessageDialog(null, "Le prix doit être un nombre ou un chiffre!");
				}
			try {
				int nombre = Integer.parseInt(jTextField_Quantity.getText());

			} catch(NumberFormatException e1) {
				showMessageDialog(null, "La quantité doit être un nombre ou un chiffre!");
			}

			try {
				int nombre = Integer.parseInt(jTextField_Libelle.getText());

			} catch(NumberFormatException e1) {
				showMessageDialog(null, "Le libellé du médicament doit être un nombre ou un chiffre!");
			}
			
			try {
				int nombre = Integer.parseInt(jTextField_Quantity_stock.getText());

			} catch(NumberFormatException e1) {
				showMessageDialog(null, "Le quantité du stock doit être un nombre ou un chiffre!");
			}
			
			if(dateMedoc.compareTo(dateNow)==0){
				showMessageDialog(null, "Vous ne pouvez pas mettre la date d'aujourd'hui comme date de péremption.");
           }
           else {

    		Manager.GestioStockManager.ModifierProduit(jTextField_Id.getText(), jTextField_Libelle.getText(), jTextField_Med_Code.getText(), jTextField_Name.getText(), 
    				 dateMedoc, jTextField_Laboratory.getText(), jTextField_Description.getText(), jTextField_Quantity.getText(), 
    				 jTextField_Quantity_type.getText(), jTextField_Med_type.getText(), jTextField_Med_use.getText(),
    				 jTextField_Price.getText(),(String) comboBox_Toxicity.getSelectedItem(),jTextField_Quantity_stock.getText());
    				 DefaultTableModel model = (DefaultTableModel)jTable_Display_Medoc.getModel();
    				 model.setRowCount(0);
    				 Show_Users_In_JTable();
		}}
        });
		
		GroupLayout gl_jPanel1 = new GroupLayout(frame.getContentPane());
		gl_jPanel1.setHorizontalGroup(
			gl_jPanel1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_jPanel1.createSequentialGroup()
					.addGroup(gl_jPanel1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_jPanel1.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_jPanel1.createParallelGroup(Alignment.LEADING)
								.addGroup(Alignment.TRAILING, gl_jPanel1.createSequentialGroup()
									.addComponent(lblNewLabel_10)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(comboBox_Toxicity, 0, 273, Short.MAX_VALUE))
								.addGroup(Alignment.TRAILING, gl_jPanel1.createSequentialGroup()
									.addComponent(btnNewButton)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(jButton_Update)
									.addGap(26))
								.addComponent(jTextField_Quantity_stock, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 178, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_4)
								.addComponent(lblNewLabel_6)
								.addComponent(lblNewLabel_7)
								.addComponent(lblNewLabel_8)
								.addComponent(lblNewLabel_9)
								.addComponent(lblNewLabel)
								.addComponent(lblNewLabel_13)
								.addGroup(gl_jPanel1.createSequentialGroup()
									.addGroup(gl_jPanel1.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_jPanel1.createParallelGroup(Alignment.LEADING, false)
											.addComponent(lblNewLabel_3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
											.addComponent(lblNewLabel_12, 0, 0, Short.MAX_VALUE))
										.addComponent(lblNewLabel_1)
										.addComponent(lblNewLabel_2)
										.addComponent(lblNewLabel_5))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_jPanel1.createParallelGroup(Alignment.LEADING)
										.addComponent(JdateChooser, GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE)
										.addComponent(jTextField_Med_Code, 175, 181, Short.MAX_VALUE)
										.addComponent(jTextField_Name, 175, 181, Short.MAX_VALUE)
										.addComponent(jTextField_Laboratory, 175, 181, Short.MAX_VALUE)
										.addComponent(jTextField_Description, 175, 181, Short.MAX_VALUE)
										.addComponent(jTextField_Quantity, 175, 181, Short.MAX_VALUE)
										.addComponent(jTextField_Quantity_type, 175, 181, Short.MAX_VALUE)
										.addComponent(jTextField_Med_type, GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE)
										.addComponent(jTextField_Med_use, GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE)
										.addComponent(jTextField_Price, GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE)
										.addComponent(jTextField_Id, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE)
										.addComponent(jTextField_Libelle, Alignment.TRAILING, 175, 181, Short.MAX_VALUE))))
							.addGap(18))
						.addGroup(gl_jPanel1.createSequentialGroup()
							.addGap(25)
							.addComponent(lblNewLabel_11, GroupLayout.PREFERRED_SIZE, 326, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_jPanel1.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblNewLabel_14)))
					.addGap(18)
					.addGroup(gl_jPanel1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_jPanel1.createSequentialGroup()
							.addComponent(lblNewLabel_15)
							.addGap(18)
							.addComponent(JtextField_Search, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(24))
						.addGroup(gl_jPanel1.createSequentialGroup()
							.addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 491, Short.MAX_VALUE)
							.addContainerGap())))
		);
		gl_jPanel1.setVerticalGroup(
			gl_jPanel1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_jPanel1.createSequentialGroup()
					.addGroup(gl_jPanel1.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_jPanel1.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblNewLabel_11, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
							.addGap(11)
							.addGroup(gl_jPanel1.createParallelGroup(Alignment.BASELINE)
								.addComponent(jTextField_Id, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_1))
							.addGap(9)
							.addGroup(gl_jPanel1.createParallelGroup(Alignment.BASELINE)
								.addComponent(jTextField_Libelle, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_2))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_jPanel1.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_3)
								.addComponent(jTextField_Med_Code, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(8)
							.addGroup(gl_jPanel1.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel_4)
								.addComponent(jTextField_Name, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(11)
							.addGroup(gl_jPanel1.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblNewLabel_5)
								.addComponent(JdateChooser, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(12)
							.addGroup(gl_jPanel1.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_6)
								.addComponent(jTextField_Laboratory, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_jPanel1.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_7)
								.addComponent(jTextField_Description, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(11)
							.addGroup(gl_jPanel1.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_8)
								.addComponent(jTextField_Quantity, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(11)
							.addGroup(gl_jPanel1.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_9)
								.addComponent(jTextField_Quantity_type, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(11)
							.addGroup(gl_jPanel1.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel)
								.addComponent(jTextField_Med_type, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(10)
							.addGroup(gl_jPanel1.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_12)
								.addComponent(jTextField_Med_use, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(7)
							.addGroup(gl_jPanel1.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_13)
								.addComponent(jTextField_Price, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(7)
							.addGroup(gl_jPanel1.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_10)
								.addComponent(comboBox_Toxicity, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(19)
							.addGroup(gl_jPanel1.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblNewLabel_14)
								.addComponent(jTextField_Quantity_stock, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(32)
							.addGroup(gl_jPanel1.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
								.addComponent(jButton_Update, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_jPanel1.createSequentialGroup()
							.addGap(69)
							.addGroup(gl_jPanel1.createParallelGroup(Alignment.BASELINE)
								.addComponent(JtextField_Search, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_15))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(40, Short.MAX_VALUE))
		);
       
		frame.getContentPane().setLayout(gl_jPanel1);
        pack();
    }                     
                                          
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ModifierMedoc window = new ModifierMedoc();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
        });
    } 
}

