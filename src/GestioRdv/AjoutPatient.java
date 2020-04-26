package GestioRdv;

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
import static javax.swing.JOptionPane.showMessageDialog;
import javax.swing.DefaultComboBoxModel;


public class AjoutPatient {

	private JDialog frame;
	private JTextField Nom;
	private JTextField Prenom;
	private JTextField Mail;
	private JTextField Portable;
	private JTextField Fixe;
	private JTextField Adresse;
	private JTextField Mutuelle;
	private JTextField toxicite;
	private JTextField Num_secu;
	Toolkit toolkit = Toolkit.getDefaultToolkit();
	Dimension screenSize = toolkit.getScreenSize();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AjoutPatient window = new AjoutPatient();
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
	public AjoutPatient() {
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
		frame.setBounds(100, 100, 600, 500);
		frame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		int x = (screenSize.width - frame.getWidth()) / 2;
		int y = (screenSize.height - frame.getHeight()) / 2;
		frame.setLocation(x,y);
		
		
		JLabel lblAjouterUnMdicament = new JLabel("Ajouter un patient");
		lblAjouterUnMdicament.setFont(new Font("Tahoma", Font.PLAIN, 24));
		
		Nom = new JTextField();
		Nom.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Nom");
		
		Prenom = new JTextField();
		Prenom.setColumns(10);
		
		Mail = new JTextField();
		Mail.setColumns(10);
		
		JLabel prenom = new JLabel("Pr\u00E9nom");
		
		JLabel lblNewLabel_2 = new JLabel("Mail");
		
		JLabel lblNewLabel_3 = new JLabel("T\u00E9l\u00E9phone Portable");
		
		Portable = new JTextField();
		Portable.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("T\u00E9l\u00E9phone Fixe");
		
		Fixe = new JTextField();
		Fixe.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("Adresse");
		
		Adresse = new JTextField();
		Adresse.setColumns(10);
		
		Mutuelle = new JTextField();
		Mutuelle.setColumns(10);
		
		JLabel lblNewLabel_7 = new JLabel("Mutuelle");
		
		JLabel lblNewLabel_8 = new JLabel("Groupe sanguin");
		
		JLabel lblNewLabel_9 = new JLabel("Sexe");
		
		JComboBox Sexe = new JComboBox();
		Sexe.setModel(new DefaultComboBoxModel(new String[] {"Homme", "Femme", "Autres"}));
		
		JComboBox grpSanguin = new JComboBox();
		grpSanguin.setModel(new DefaultComboBoxModel(new String[] {"A-", "A+", "AB-", "AB+", "B-", "B+", "O-", "O+"}));
		
		// Ajout patient
		JButton btnAjouter = new JButton("Ajouter");
		btnAjouter.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String email = Mail.getText();
				String regexStr = "^[0-9]*$";
                if (Portable.getText().trim().matches(regexStr) && Portable.getText().length() == 10) {
                    if(Fixe.getText().trim().matches(regexStr) && Fixe.getText().length() == 10) {
                        if(Num_secu.getText().trim().matches(regexStr) && Num_secu.getText().length() == 15) {
                        	if (email.contains("@")) {
                        		Manager.GestioRdvManager.AjoutPatient(Nom.getText(), Prenom.getText(), Portable.getText(), Fixe.getText(), Mail.getText(), Adresse.getText(), Num_secu.getText(), Mutuelle.getText(), (String) Sexe.getSelectedItem(), (String) grpSanguin.getSelectedItem());
                            	//frame.dispose();
                        	}
                            else {
           						 showMessageDialog(null, "Veuillez saisir un mail valide ! Par exemple : test@mail.fr");
                            }
                      } else{
                        showMessageDialog(null, "Le numéro de sécurité sociale doit contenir 15 chiffres !");
                        }
                    }
                    else{
                    	showMessageDialog(null, "Le numéro fixe doit contenir 10 chiffres !");
                    }
                }
                else{
                	showMessageDialog(null, "Le numéro de portable doit contenir 10 chiffres !");
                }
            }

		});
		
		JButton btnRetour = new JButton("Retour");
		btnRetour.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
			}
		});
		
		JLabel lblNewLabel_1 = new JLabel("Num\u00E9ro de s\u00E9curit\u00E9 sociale");
		
		Num_secu = new JTextField();
		Num_secu.setColumns(10);

		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(211, Short.MAX_VALUE)
					.addComponent(lblAjouterUnMdicament)
					.addGap(191))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(134)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(Nom, GroupLayout.PREFERRED_SIZE, 161, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE)
						.addComponent(prenom, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
							.addComponent(Fixe, Alignment.LEADING)
							.addComponent(Portable, Alignment.LEADING)
							.addComponent(Mail, Alignment.LEADING)
							.addComponent(Prenom, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
							.addComponent(lblNewLabel_2, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblNewLabel_3, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblNewLabel_4, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE))
					.addGap(54)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblNewLabel_1)
							.addContainerGap())
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addGroup(groupLayout.createSequentialGroup()
								.addComponent(btnRetour)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(btnAjouter, GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE)
								.addGap(92))
							.addGroup(groupLayout.createSequentialGroup()
								.addComponent(lblNewLabel_9, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
								.addContainerGap())
							.addGroup(groupLayout.createSequentialGroup()
								.addComponent(lblNewLabel_8)
								.addContainerGap())
							.addGroup(groupLayout.createSequentialGroup()
								.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
									.addComponent(Sexe, Alignment.LEADING, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(grpSanguin, Alignment.LEADING, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(Adresse, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
									.addComponent(lblNewLabel_6, Alignment.LEADING)
									.addComponent(lblNewLabel_7, Alignment.LEADING)
									.addComponent(Mutuelle, Alignment.LEADING)
									.addComponent(Num_secu, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE))
								.addContainerGap(96, GroupLayout.PREFERRED_SIZE)))))
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
						.addComponent(Nom, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(Adresse, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(prenom)
						.addComponent(lblNewLabel_7))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(Prenom, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(Mutuelle, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_2)
						.addComponent(lblNewLabel_1))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(Mail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(Num_secu, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_3)
						.addComponent(lblNewLabel_8))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(Portable, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(grpSanguin, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_4)
						.addComponent(lblNewLabel_9))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(Fixe, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(Sexe, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(28)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(btnRetour, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnAjouter, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addContainerGap(94, Short.MAX_VALUE))
		);
		frame.getContentPane().setLayout(groupLayout);
	}
}
