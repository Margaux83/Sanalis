package GestioStock;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import net.miginfocom.swing.MigLayout;
import java.awt.GridLayout;
import javax.swing.JTabbedPane;
import javax.swing.JLayeredPane;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JToolBar;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.SystemColor;
import java.awt.Toolkit;
import javax.swing.UIManager;
import javax.swing.ImageIcon;

public class MainStock {

	private JFrame frameMain;
	Toolkit toolkit = Toolkit.getDefaultToolkit();
	Dimension screenSize = toolkit.getScreenSize();


	/**
	 * Launch the application.
	 */
	public static void main(String[] args, Integer id) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainStock window = new MainStock(id);
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
	public MainStock(int id) {
		initialize(id);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(int id) {
		frameMain = new JFrame();
		frameMain.setBounds(100, 100, 750, 500);
		frameMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		int x = (screenSize.width - frameMain.getWidth()) / 2;
		int y = (screenSize.height - frameMain.getHeight()) / 2;
		frameMain.setLocation(x,y);
		frameMain.setResizable(false);

		
		JLabel lblNewLabel = new JLabel("Bienvenue sur Sanalis (Espace Gestion de Stock)");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 28));
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("img/logo_sanalis.png"));
		
		JButton btnAjouter = new JButton("Ajouter un produit");
		btnAjouter.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				GestioStock.AjoutMedoc AjoutMedoc = new GestioStock.AjoutMedoc();
				AjoutMedoc.main(null);
			}
		});
		
		JButton btnModifier = new JButton("Modifier un produit");
		btnModifier.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
			GestioStock.ModifierMedoc ModifierMedoc = new GestioStock.ModifierMedoc();
			ModifierMedoc.main(null);
			}
		});
		
		JButton btnSupprimer = new JButton("Supprimer un produit");
		btnSupprimer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				GestioStock.SupprimerMedoc SupprimerMedoc = new GestioStock.SupprimerMedoc();
				SupprimerMedoc.main(null);
			}
		});
		
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
		
		JButton btnStock = new JButton("Stock");
		btnStock.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				GestioStock.StockMedoc StockMedoc = new GestioStock.StockMedoc();
				StockMedoc.main(null);
			}
		});
		
		JButton btnCommande = new JButton("Commandes des stocks");
		btnCommande.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				GestioStock.CommandeMedoc CommandeMedoc = new GestioStock.CommandeMedoc();
				CommandeMedoc.main(null);
			}
		});
		
		JButton btnModifierProfil = new JButton("Modifier mon profil");
		btnModifierProfil.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				GestioStock.ModifProfilStock ModifProfilStock = new GestioStock.ModifProfilStock(id);
				ModifProfilStock.main(null, id);
			}
		});
		
		GroupLayout groupLayout = new GroupLayout(frameMain.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(38)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 619, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(deconnexion, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
					.addGap(20))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(266, Short.MAX_VALUE)
					.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 204, GroupLayout.PREFERRED_SIZE)
					.addGap(264))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(83)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnStock, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)
						.addComponent(btnAjouter, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE))
					.addGap(45)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnCommande, GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)
						.addComponent(btnModifier, GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE))
					.addGap(34)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(btnModifierProfil, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnSupprimer, GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE))
					.addGap(93))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 179, GroupLayout.PREFERRED_SIZE)
					.addGap(52)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnSupprimer, GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE)
						.addComponent(btnModifier, GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE)
						.addComponent(btnAjouter, GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnModifierProfil, GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
						.addComponent(btnCommande, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnStock, GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE))
					.addGap(28))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(20)
					.addComponent(deconnexion, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(400, Short.MAX_VALUE))
		);
		frameMain.getContentPane().setLayout(groupLayout);
	}
}
