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

public class AjoutSalarie {

	private JDialog frame;
	private JTextField Nom;
	private JTextField Prenom;
	private JTextField Mail;
	private JLabel lblMail;
	private JLabel lblMotDePasse;
	private JTextField MDP;
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
					AjoutSalarie window = new AjoutSalarie();
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
	public AjoutSalarie() {
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
		
		JLabel lblAjouterunsalarie = new JLabel("Ajouter un salari\u00E9");
		lblAjouterunsalarie.setFont(new Font("Tahoma", Font.PLAIN, 25));
		
		Nom = new JTextField();
		Nom.setColumns(10);
		
		Prenom = new JTextField();
		Prenom.setColumns(10);
		
		Mail = new JTextField();
		Mail.setColumns(10);
		
		JLabel lblNom = new JLabel("Nom");
		
		JLabel lblPrenom = new JLabel("Prénom");
		
		lblMail = new JLabel("Mail");
		
		lblMotDePasse = new JLabel("Mot de passe");
		
		MDP = new JPasswordField();
		MDP.setColumns(10);
		
		JLabel lblTlphone = new JLabel("T\u00E9l\u00E9phone");
		
		Portable = new JTextField();
		Portable.setColumns(10);
		
		JLabel lblRole = new JLabel("Role");
		JComboBox Role = new JComboBox();
		Role.addItem("ADMIN");
        Role.addItem("GESTIOSTOCK");
        Role.addItem("GESTIORDV");

		// Ajout du salarié
		JButton btnAjouter = new JButton("Ajouter");
		btnAjouter.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String email = Mail.getText();
				String regexStr = "^[0-9]*$";
				if (Portable.getText().trim().matches(regexStr) && Portable.getText().length() == 10) {
					 if (email.contains("@")) 
						 AdminManager.AjoutPersonnel(Nom.getText(), Prenom.getText(), Portable.getText(), Mail.getText(), MDP.getText(), (String) Role.getSelectedItem());
					 else
							showMessageDialog(null, "Veuillez saisir un mail valide ! Par exemple : test@mail.fr");
					 }
				else {
					showMessageDialog(null, "Le numéro de portable est erroné ! Le numéro de portable doit contenir 10 chiffres");
				}
			}});
		
		//Bouton Retour
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
						.addComponent(lblTlphone, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE))
					.addGap(95)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblRole)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addComponent(Role, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblMotDePasse, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblMail, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
								.addComponent(MDP, GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
								.addComponent(Mail, 176, 176, 176))))
					.addGap(75))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(203, Short.MAX_VALUE)
					.addComponent(lblAjouterunsalarie, GroupLayout.PREFERRED_SIZE, 206, GroupLayout.PREFERRED_SIZE)
					.addGap(185))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(352, Short.MAX_VALUE)
					.addComponent(btnRetour)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnAjouter)
					.addGap(98))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(28)
					.addComponent(lblAjouterunsalarie, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
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
						.addComponent(lblMotDePasse))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(Prenom, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
						.addComponent(MDP, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTlphone)
						.addComponent(lblRole))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(Portable, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
						.addComponent(Role, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnRetour)
						.addComponent(btnAjouter))
					.addContainerGap(142, Short.MAX_VALUE))
		);
		frame.getContentPane().setLayout(groupLayout);
	}
}
