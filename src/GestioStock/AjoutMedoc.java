package GestioStock;

import static javax.swing.JOptionPane.showMessageDialog;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Dialog.ModalityType;
import java.awt.Window.Type;

import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.text.SimpleDateFormat;

import static javax.swing.JOptionPane.showMessageDialog;
import com.toedter.calendar.JDateChooser;


public class AjoutMedoc {

	private JDialog frame;
	private JTextField nom_generique;
	private JTextField laboratoire;
	private JTextField description;
	private JTextField qte;
	private JTextField type_solution;
	private JTextField code_medicament;
	private JTextField indication;
	private JTextField libelle_medoc;
	private JTextField toxicite;
	private JTextField prix;
	Toolkit toolkit = Toolkit.getDefaultToolkit();
	Dimension screenSize = toolkit.getScreenSize();
	private JTextField mg;
	private JTextField quantite_stock;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AjoutMedoc window = new AjoutMedoc();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AjoutMedoc() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JDialog();
		frame.setModalityType(ModalityType.APPLICATION_MODAL);
		frame.setResizable(false);
		frame.setType(Type.POPUP);
		frame.setBounds(100, 100, 600, 550);
		frame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		int x = (screenSize.width - frame.getWidth()) / 2;
		int y = (screenSize.height - frame.getHeight()) / 2;
		frame.setLocation(x,y);
		
		
		JLabel lblAjouterUnMdicament = new JLabel("Ajouter un m\u00E9dicament");
		lblAjouterUnMdicament.setFont(new Font("Tahoma", Font.PLAIN, 24));
		
		nom_generique = new JTextField();
		nom_generique.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Nom générique");
		
		laboratoire = new JTextField();
		laboratoire.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Date de péremption");
		
		JLabel lblNewLabel_2 = new JLabel("Laboratoire");
		
		JLabel lblNewLabel_3 = new JLabel("Description");
		
		description = new JTextField();
		description.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Quantit\u00E9");
		
		qte = new JTextField();
		qte.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("Type de solution");
		
		type_solution = new JTextField();
		type_solution.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("Code de médicament");
		
		code_medicament = new JTextField();
		code_medicament.setColumns(10);
		
		indication = new JTextField();
		indication.setColumns(10);
		
		JLabel lblNewLabel_7 = new JLabel("Indication d'utilisation");
		
		JLabel lblNewLabel_8 = new JLabel("Libellé médicament");
		
		libelle_medoc = new JTextField();
		libelle_medoc.setColumns(10);
		
		JLabel lblNewLabel_9 = new JLabel("Toxicité");
		
		JComboBox toxicite = new JComboBox();
		toxicite.addItem("Niveaux 1");
        toxicite.addItem("Niveaux 2");
        toxicite.addItem("Niveaux 3");
		
		prix = new JTextField();
		prix.setColumns(10);
		
		mg = new JTextField();
		mg.setColumns(10);
		
		JLabel lblMg = new JLabel("mg, g, ml");
		
		JLabel lblPrix = new JLabel("Prix");
		
		
		JLabel lblNewLabel_10 = new JLabel("Quantit\u00E9 de stock");
		
		quantite_stock = new JTextField();
		quantite_stock.setColumns(10);
		
		JDateChooser dateChooser = new JDateChooser();

        Date date = new Date(System.currentTimeMillis());

	    dateChooser.setMinSelectableDate(date);
	
		//Btn Ajout
		JButton btnAjouter = new JButton("Ajouter");
		btnAjouter.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				//formatage de dateChooser
				 SimpleDateFormat tdate = new SimpleDateFormat("dd-MM-yyyy");
		         String dateMedoc = tdate.format(dateChooser.getDate());
				 
				 String dateNow = tdate.format(date);
				 
				try {
					int nombre = Integer.parseInt(prix.getText());

				} catch(NumberFormatException e1) {
					showMessageDialog(null, "Le prix doit être un nombre ou un chiffre!");
				}
			try {
				int nombre = Integer.parseInt(qte.getText());

			} catch(NumberFormatException e1) {
				showMessageDialog(null, "La quantité doit être un nombre ou un chiffre!");
			}

			try {
				int nombre = Integer.parseInt(libelle_medoc.getText());

			} catch(NumberFormatException e1) {
				showMessageDialog(null, "Le libellé du médicament doit être un nombre ou un chiffre!");
			}
			
			try {
				int nombre = Integer.parseInt(quantite_stock.getText());

			} catch(NumberFormatException e1) {
				showMessageDialog(null, "Le quantité du stock doit être un nombre ou un chiffre!");
			}
			
	           if(dateMedoc.compareTo(dateNow)==0){
					showMessageDialog(null, "Vous ne pouvez pas mettre la date d'aujourd'hui comme date de péremption.");
	           }
	           else {
    		Manager.GestioStockManager.AjoutProduit(dateMedoc, Integer.parseInt(prix.getText()), (String) toxicite.getSelectedItem(), mg.getText(), Integer.parseInt(qte.getText()), 
    	    		type_solution.getText(), description.getText(), laboratoire.getText(), indication.getText(), Integer.parseInt(libelle_medoc.getText()), code_medicament.getText(), nom_generique.getText(), Integer.parseInt(quantite_stock.getText()));
		}}
			
		});
		
		JButton btnRetour = new JButton("Retour");
		btnRetour.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
			}
		});
				
		
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(134)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(nom_generique, GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)
								.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_4, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
								.addComponent(type_solution)
								.addComponent(qte)
								.addComponent(description)
								.addComponent(laboratoire)
								.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_3, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_5, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lblNewLabel_10)
								.addComponent(quantite_stock)
								.addComponent(dateChooser, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addGap(53)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
									.addComponent(lblMg, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
										.addComponent(lblPrix, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
										.addComponent(mg)
										.addComponent(code_medicament, GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
										.addComponent(lblNewLabel_6)
										.addComponent(lblNewLabel_7)
										.addComponent(indication)
										.addComponent(lblNewLabel_8, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(libelle_medoc)
										.addComponent(lblNewLabel_9, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
										.addComponent(toxicite, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(prix)))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(btnRetour)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnAjouter, GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED))))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(161)
							.addComponent(lblAjouterUnMdicament, GroupLayout.PREFERRED_SIZE, 266, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(93, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblAjouterUnMdicament, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(lblNewLabel_6))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(nom_generique, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(code_medicament, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(lblNewLabel_7))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(indication, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(dateChooser, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_2)
						.addComponent(lblNewLabel_8))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(laboratoire, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(libelle_medoc, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_3)
						.addComponent(lblNewLabel_9))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(description, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(toxicite, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_4)
						.addComponent(lblMg))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(qte, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(mg, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_5)
						.addComponent(lblPrix))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(type_solution, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(prix, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblNewLabel_10)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(quantite_stock, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnRetour, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnAjouter))
					.addContainerGap(100, Short.MAX_VALUE))
		);
		frame.getContentPane().setLayout(groupLayout);
	}
}
