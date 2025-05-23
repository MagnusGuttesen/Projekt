package ui;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.awt.Font;
import javax.swing.JScrollPane;
import java.awt.Color;

public class RBekræftetUI extends JDialog {

    private static final long serialVersionUID = 1L;
    private HovedmenuUI hovedmenuUI;
    private JTable table;

    public static void main(String[] args) {
        try {
            LogIndUI logIndUI = new LogIndUI();
            HovedmenuUI hovedmenuUI = new HovedmenuUI(logIndUI);
            DefaultTableModel tableModel = new DefaultTableModel(new Object[] { "Navn", "ID", "BurNr" }, 0);
            RBekræftetUI dialog = new RBekræftetUI(hovedmenuUI, tableModel);
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public RBekræftetUI(HovedmenuUI hovedmenuUI, DefaultTableModel tableModel) {
        this.hovedmenuUI = hovedmenuUI;

        setBounds(100, 100, 648, 500);
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[] { 49, 0, 207, 113, 0, 0, 0, 0 };
        gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
        gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
        gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0,
                Double.MIN_VALUE };
        getContentPane().setLayout(gridBagLayout);

        JLabel lblNewLabel = new JLabel("Produkt registreret!");
        lblNewLabel.setForeground(new Color(64, 0, 128));
        lblNewLabel.setFont(new Font("Cambria", Font.BOLD, 18));
        GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
        gbc_lblNewLabel.fill = GridBagConstraints.VERTICAL;
        gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
        gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel.gridx = 2;
        gbc_lblNewLabel.gridy = 1;
        getContentPane().add(lblNewLabel, gbc_lblNewLabel);

        JScrollPane scrollPane = new JScrollPane();
        GridBagConstraints gbc_scrollPane = new GridBagConstraints();
        gbc_scrollPane.gridwidth = 2;
        gbc_scrollPane.gridheight = 5;
        gbc_scrollPane.fill = GridBagConstraints.BOTH;
        gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
        gbc_scrollPane.gridx = 2;
        gbc_scrollPane.gridy = 3;
        getContentPane().add(scrollPane, gbc_scrollPane);

        table = new JTable(tableModel);
        table.setFont(new Font("Cambria", Font.PLAIN, 12));
        scrollPane.setViewportView(table);

        JButton btnTilbagetilmenu = new JButton("Tilbage til menu");
        btnTilbagetilmenu.setFont(new Font("Cambria", Font.PLAIN, 12));
        btnTilbagetilmenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                if (hovedmenuUI != null) {
                    hovedmenuUI.setVisible(true);
                }
            }
        });

        GridBagConstraints gbc_btnTilbagetilmenu = new GridBagConstraints();
        gbc_btnTilbagetilmenu.insets = new Insets(0, 0, 5, 5);
        gbc_btnTilbagetilmenu.anchor = GridBagConstraints.EAST;
        gbc_btnTilbagetilmenu.fill = GridBagConstraints.VERTICAL;
        gbc_btnTilbagetilmenu.gridx = 3;
        gbc_btnTilbagetilmenu.gridy = 9;
        getContentPane().add(btnTilbagetilmenu, gbc_btnTilbagetilmenu);
    }
}
