package ui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import db.DataAccessException;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class HovedmenuUI extends JFrame {
	private LogIndUI window;

	// Konstruktøren opbygger GUI'en for hovedmenuen
	public HovedmenuUI(LogIndUI window) {
		this.window = window;

		// Layoutet defineres med GridBagLayout, som giver fleksibel placering af komponenter
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 32, 0, 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] {};
		gridBagLayout.rowWeights = new double[] {};
		setBounds(200, 200, 706, 362);

		GridBagConstraints gbc_lblVelkommen = new GridBagConstraints();
		gbc_lblVelkommen.insets = new Insets(0, 0, 5, 5);
		gbc_lblVelkommen.gridx = 1;
		gbc_lblVelkommen.gridy = 0;
		GridBagLayout gridBagLayout_1 = new GridBagLayout();
		gridBagLayout_1.columnWidths = new int[] { 51, 1, 0, 0, 0, 0, 105, 0 };
		gridBagLayout_1.rowHeights = new int[] { 1, 0, 61, 0, 0, 44, 36, 38, 0 };
		gridBagLayout_1.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gridBagLayout_1.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		getContentPane().setLayout(gridBagLayout_1);

		// Opretter overskrift med titel "Hovedmenu"
		JLabel lblHovedmenu = new JLabel("Hovedmenu");
		lblHovedmenu.setForeground(new Color(64, 0, 128));
		lblHovedmenu.setFont(new Font("Cambria", Font.BOLD, 18));
		GridBagConstraints gbc_lblHovedmenu_1 = new GridBagConstraints();
		gbc_lblHovedmenu_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblHovedmenu_1.fill = GridBagConstraints.BOTH;
		gbc_lblHovedmenu_1.gridx = 1;
		gbc_lblHovedmenu_1.gridy = 1;
		getContentPane().add(lblHovedmenu, gbc_lblHovedmenu_1);
		
		// Knap til visning af produkter i et bur
		JButton btnSeProdukter = new JButton("Se produkter i bur");
		btnSeProdukter.addActionListener(e -> {
			setVisible(false);
		    new BurOversigtUI(this).setVisible(true); // Åbner ny frame for burvisning
		    
		});
		add(btnSeProdukter);

		// Knap til åbning af registreringsvinduet for produkter i bur
		JButton btnBur = new JButton("Bur");
		btnBur.setFont(new Font("Cambria", Font.PLAIN, 12));
		GridBagConstraints gbc_btnBur_1 = new GridBagConstraints();
		gbc_btnBur_1.insets = new Insets(0, 0, 5, 5);
		gbc_btnBur_1.fill = GridBagConstraints.BOTH;
		gbc_btnBur_1.gridx = 1;
		gbc_btnBur_1.gridy = 3;
		getContentPane().add(btnBur, gbc_btnBur_1);

		btnBur.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false); 
				RegisterProductUI dialog = null;
				try {
					dialog = new RegisterProductUI(HovedmenuUI.this);
				} catch (DataAccessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				dialog.setVisible(true);
			}
		});
	

		// Placeholder-knapper til fremtidige funktioner (statistik, genbestilling, lagerstyring)
		JButton btnGenbestil = new JButton("Genbestil");
		btnGenbestil.setFont(new Font("Cambria", Font.PLAIN, 12));
		GridBagConstraints gbc_btnGenbestil = new GridBagConstraints();
		gbc_btnGenbestil.insets = new Insets(0, 0, 5, 5);
		gbc_btnGenbestil.gridx = 3;
		gbc_btnGenbestil.gridy = 3;
		getContentPane().add(btnGenbestil, gbc_btnGenbestil);

		JButton btnSS = new JButton("Salgstatistik");
		btnSS.setFont(new Font("Cambria", Font.PLAIN, 12));
		GridBagConstraints gbc_btnSS = new GridBagConstraints();
		gbc_btnSS.insets = new Insets(0, 0, 5, 5);
		gbc_btnSS.gridx = 5;
		gbc_btnSS.gridy = 3;
		getContentPane().add(btnSS, gbc_btnSS);

		JButton btnLstyring = new JButton("Lagerstyring");
		btnLstyring.setFont(new Font("Cambria", Font.PLAIN, 12));
		GridBagConstraints gbc_btnLstyring = new GridBagConstraints();
		gbc_btnLstyring.insets = new Insets(0, 0, 5, 5);
		gbc_btnLstyring.gridx = 5;
		gbc_btnLstyring.gridy = 4;
		getContentPane().add(btnLstyring, gbc_btnLstyring);

		// Knap til at logge ud og vende tilbage til login-vinduet
		JButton btnLogud_1 = new JButton("Log ud");
		btnLogud_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose(); // Lukker nuværende vindue
				new LogIndUI(); // Går tilbage til login
				
                
			}
		});
		btnLogud_1.setFont(new Font("Cambria", Font.PLAIN, 12));
		GridBagConstraints gbc_btnLogud_1 = new GridBagConstraints();
		gbc_btnLogud_1.gridx = 6;
		gbc_btnLogud_1.gridy = 7;
		getContentPane().add(btnLogud_1, gbc_btnLogud_1);
		GridBagConstraints gbc_lblHovedmenu = new GridBagConstraints();
		gbc_lblHovedmenu.insets = new Insets(0, 0, 5, 5);
		gbc_lblHovedmenu.gridx = 1;
		gbc_lblHovedmenu.gridy = 1;
		GridBagConstraints gbc_btnBur = new GridBagConstraints();
		gbc_btnBur.insets = new Insets(0, 0, 0, 5);
		gbc_btnBur.anchor = GridBagConstraints.WEST;
		gbc_btnBur.gridx = 1;
		gbc_btnBur.gridy = 3;
	}
}
