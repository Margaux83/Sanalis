package Admin;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;

import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingConstants;

public class MainAdmin {

	private JFrame frameMain;
	Toolkit toolkit = Toolkit.getDefaultToolkit();
	Dimension screenSize = toolkit.getScreenSize();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainAdmin window = new MainAdmin();
					window.frameMain.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainAdmin() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frameMain = new JFrame();
		frameMain.setBounds(100, 100, 750, 500);
		frameMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		int x = (screenSize.width - frameMain.getWidth()) / 2;
		int y = (screenSize.height - frameMain.getHeight()) / 2;
		frameMain.setLocation(x,y);
		frameMain.setResizable(false);

		
		JLabel lblBienvenue = new JLabel("Bienvenue sur Sanalis (Espace Admin)");
		lblBienvenue.setFont(new Font("Tahoma", Font.PLAIN, 28));
		
		JLabel lblLogo = new JLabel("");
		lblLogo.setIcon(new ImageIcon("img/logo_sanalis.png"));
		
		//Bouton Ajouter un salarié
		JButton btnAjouterSalarie = new JButton("Ajouter un salari\u00E9");
		btnAjouterSalarie.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Admin.AjoutSalarie AjoutSalarie = new Admin.AjoutSalarie();
				AjoutSalarie.main(null);
			}
		});
		
		//Bouton Modifier un salarié
		JButton btnModifierSalarie = new JButton("Modifier un salari\u00E9");
		btnModifierSalarie.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Admin.ModifierSalarie ModifierSalarie = new Admin.ModifierSalarie();
				ModifierSalarie.main(null);
			}
		});
		
		//Bouton Supprimer un salarié
		JButton btnSupprimerSalarie = new JButton("Supprimer un salari\u00E9");
		btnSupprimerSalarie.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Admin.SupprimerSalarie SupprimerSalarie = new Admin.SupprimerSalarie();
				SupprimerSalarie.main(null);
			}
		});
		
		//Bouton Déconnexion
		JButton deconnexion = new JButton("");
		deconnexion.setIcon(new ImageIcon("img/deconnexion2.png"));
		deconnexion.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Main.Main Main = new Main.Main();
				Main.main(null);
				frameMain.dispose();
			}
		});
		
		//Bouton Ajouter un médecin
		JButton btnAjouterMedecin = new JButton("Ajouter un m\u00E9decin");
		btnAjouterMedecin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Admin.AjoutMedecin AjoutMedecin = new Admin.AjoutMedecin();
				AjoutMedecin.main(null);
			}
		});
		
		//Bouton Modifier un médecin
		JButton btnModifierMedecin = new JButton("Modifier un m\u00E9decin");
		btnModifierMedecin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Admin.ModifierMedecin ModifierMedecin = new Admin.ModifierMedecin();
				ModifierMedecin.main(null);
			}
		});
		
		//Bouton Supprimer un médecin
		JButton btnSupprimerMedecin = new JButton("Supprimer un m\u00E9decin");
		btnSupprimerMedecin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Admin.SupprimerMedecin SupprimerMedecin = new Admin.SupprimerMedecin();
				SupprimerMedecin.main(null);
			}
		});
		
		GroupLayout groupLayout = new GroupLayout(frameMain.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(265)
							.addComponent(lblLogo, GroupLayout.PREFERRED_SIZE, 204, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap(125, Short.MAX_VALUE)
							.addComponent(lblBienvenue, GroupLayout.PREFERRED_SIZE, 542, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(deconnexion, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
					.addGap(20))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(72)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnAjouterMedecin, GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
						.addComponent(btnAjouterSalarie, GroupLayout.PREFERRED_SIZE, 176, GroupLayout.PREFERRED_SIZE))
					.addGap(45)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnModifierMedecin, GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)
						.addComponent(btnModifierSalarie, GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE))
					.addGap(34)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnSupprimerSalarie, GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE)
						.addComponent(btnSupprimerMedecin, GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE))
					.addGap(83))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblBienvenue, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblLogo, GroupLayout.PREFERRED_SIZE, 179, GroupLayout.PREFERRED_SIZE)
							.addGap(52)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnSupprimerSalarie, GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE)
								.addComponent(btnModifierSalarie, GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE)
								.addComponent(btnAjouterSalarie, GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(btnSupprimerMedecin, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnModifierMedecin, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnAjouterMedecin, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(20)
							.addComponent(deconnexion, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		frameMain.getContentPane().setLayout(groupLayout);
	}
}
