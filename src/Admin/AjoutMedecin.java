package Admin;

import static javax.swing.JOptionPane.showMessageDialog;

import java.awt.Dialog.ModalityType;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JPasswordField;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Window.Type;

import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComboBox;
import javax.swing.JDialog;

import Manager.*;

public class AjoutMedecin {

	private JDialog frame;
	private JTextField Nom;
	private JTextField Prenom;
	private JTextField Mail;
	private JLabel lblMail;
	private JLabel lblProfession;
	private JTextField Profession;
	Toolkit toolkit = Toolkit.getDefaultToolkit();
	Dimension screenSize = toolkit.getScreenSize();
	private JTextField Portable;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AjoutMedecin window = new AjoutMedecin();
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
	public AjoutMedecin() {
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
		frame.setBounds(100, 100, 600, 400);
		frame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		int x = (screenSize.width - frame.getWidth()) / 2;
		int y = (screenSize.height - frame.getHeight()) / 2;
		frame.setLocation(x,y);
		
		JLabel lblAjouterunmedecin = new JLabel("Ajouter un médecin");
		lblAjouterunmedecin.setFont(new Font("Tahoma", Font.PLAIN, 25));
		
		Nom = new JTextField();
		Nom.setColumns(10);
		
		Prenom = new JTextField();
		Prenom.setColumns(10);
		
		Mail = new JTextField();
		Mail.setColumns(10);
		
		JLabel lblNom = new JLabel("Nom");
		
		JLabel lblPrenom = new JLabel("Prénom");
		
		lblMail = new JLabel("Mail");
		
		lblProfession = new JLabel("Profession");
		
		Profession = new JTextField();
		Profession.setColumns(10);
		
		JLabel lblTelephone = new JLabel("T\u00E9l\u00E9phone");
		
		Portable = new JTextField();
		Portable.setColumns(10);

		// Ajout du médecin 
		JButton btnAjouter = new JButton("Ajouter");
		btnAjouter.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String email = Mail.getText();
				String regexStr = "^[0-9]*$";
				if (Portable.getText().trim().matches(regexStr) && Portable.getText().length() == 10) {
					 if (email.contains("@")) 
						 AdminManager.AjoutMedecin(Nom.getText(), Prenom.getText(), Portable.getText(), Mail.getText(), Profession.getText());
					 else
						 showMessageDialog(null, "Veuillez saisir un mail valide ! Par exemple : test@mail.fr");
					 }
					 else {
					showMessageDialog(null, "Le numéro de portable est erroné ! Le numéro de portable doit contenir 10 chiffres");
				}
		}});
		
		// Bouton Retour
				JButton btnRetour = new JButton("Retour");
		btnRetour.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
			}
		});	
		
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(59)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblPrenom, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNom, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
							.addComponent(Prenom, Alignment.LEADING)
							.addComponent(Nom, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE)
							.addComponent(Portable, Alignment.LEADING))
						.addComponent(lblTelephone, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE))
					.addGap(36)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(59)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblProfession, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblMail, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
								.addComponent(Profession, GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
								.addComponent(Mail, 176, 176, 176))
							.addGap(75))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(62)
							.addComponent(btnRetour)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnAjouter)
							.addContainerGap())))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(172, Short.MAX_VALUE)
					.addComponent(lblAjouterunmedecin, GroupLayout.PREFERRED_SIZE, 237, GroupLayout.PREFERRED_SIZE)
					.addGap(185))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(28)
					.addComponent(lblAjouterunmedecin, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
					.addGap(29)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNom)
						.addComponent(lblMail))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(Nom, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
						.addComponent(Mail, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPrenom)
						.addComponent(lblProfession))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(Prenom, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
						.addComponent(Profession, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblTelephone)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(Portable, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnAjouter)
						.addComponent(btnRetour))
					.addContainerGap(189, Short.MAX_VALUE))
		);
		frame.getContentPane().setLayout(groupLayout);
	}
}
