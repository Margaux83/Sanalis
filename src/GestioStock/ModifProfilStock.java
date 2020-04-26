package GestioStock;

import static javax.swing.JOptionPane.showMessageDialog;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JPasswordField;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Dialog.ModalityType;
import java.awt.Window.Type;

import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ModifProfilStock {

	private JDialog frame;
	private JTextField lastname;
	private JTextField firstname;
	private JTextField mail;
	private JLabel lblNewLabel_2;
	private JLabel lblMotDePasse;
	private JTextField password;
	private JTextField textField_4;
	Toolkit toolkit = Toolkit.getDefaultToolkit();
	Dimension screenSize = toolkit.getScreenSize();
	private JTextField phone;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args, Integer id) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ModifProfilStock window = new ModifProfilStock(id);
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
	public ModifProfilStock(int id) {
		initialize(id);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(int id) {
		frame = new JDialog();
		frame.setModalityType(ModalityType.APPLICATION_MODAL);
		frame.setResizable(false);
		frame.setType(Type.POPUP);
		frame.setBounds(100, 100, 600, 400);
		frame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		int x = (screenSize.width - frame.getWidth()) / 2;
		int y = (screenSize.height - frame.getHeight()) / 2;
		frame.setLocation(x,y);
		
		JLabel lblModificationDeProfil = new JLabel("Modification de profil");
		lblModificationDeProfil.setFont(new Font("Tahoma", Font.PLAIN, 25));
		
		lastname = new JTextField();
		lastname.setColumns(10);
		
		firstname = new JTextField();
		firstname.setColumns(10);
		
		mail = new JTextField();
		mail.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Nom");
		
		JLabel lblNewLabel_1 = new JLabel("Prénom");
		
		lblNewLabel_2 = new JLabel("Mail");
		
		lblMotDePasse = new JLabel("Mot de passe");
		
		password = new JPasswordField();
		password.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		
		JLabel lblTlphone = new JLabel("T\u00E9l\u00E9phone");
		
		phone = new JTextField();
		phone.setColumns(10);
		
		JButton btnRetour = new JButton("Retour");
		btnRetour.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
			}
		});
		
		JButton btnModifier = new JButton("Modifier");
		btnModifier.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
			String email = mail.getText();
			String regexStr = "^[0-9]*$";
        	if (phone.getText().trim().matches(regexStr) && phone.getText().length() == 10) {
            	if (email.contains("@")) {
            Manager.GestioStockManager.ModifierProfilStock(lastname.getText(), firstname.getText(), mail.getText(), password.getText(), phone.getText(), id);
            //frame.dispose();
            	}
                else {
						 showMessageDialog(null, "Veuillez saisir un mail valide ! Par exemple : test@mail.fr");
                }
        	}
    		else {
				showMessageDialog(null, "Le numéro de portable doit doit contenir 10 chiffres !");
    		}
           }
		});
		
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(153, Short.MAX_VALUE)
					.addComponent(lblModificationDeProfil, GroupLayout.PREFERRED_SIZE, 293, GroupLayout.PREFERRED_SIZE)
					.addGap(140))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(59)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblTlphone, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
								.addComponent(textField_4, 215, 215, 215)
								.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
									.addComponent(firstname, Alignment.LEADING)
									.addComponent(lastname, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE)
									.addComponent(phone, Alignment.LEADING)))
							.addGap(59)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(8)
									.addComponent(btnRetour)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(btnModifier))
								.addComponent(lblMotDePasse, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
								.addComponent(password, GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
								.addComponent(mail))))
					.addGap(75))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(29)
					.addComponent(lblModificationDeProfil, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
					.addGap(28)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(lblNewLabel_2))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lastname, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
						.addComponent(mail, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(lblMotDePasse))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(firstname, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
						.addComponent(password, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblTlphone)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(phone, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnModifier)
						.addComponent(btnRetour))
					.addGap(155)
					.addComponent(textField_4, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		frame.getContentPane().setLayout(groupLayout);
	}
}
