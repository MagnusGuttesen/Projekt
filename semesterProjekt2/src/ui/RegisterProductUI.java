package ui;

import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Date;
import controller.CageController;
import controller.ProductController;
import model.Cage;
import model.Product;
import model.RegisteredProduct;
import db.RegisteredProductDB;
import db.DataAccessException;

public class RegisterProductUI extends JDialog {

    private int cageNo;
    private ProductController productController = new ProductController();
    private CageController cageController = new CageController();
    private DefaultListModel<Product> productListModel = new DefaultListModel<>();
    private DefaultListModel<Cage> cageListModel = new DefaultListModel<>();
    private JTable table;
    private DefaultTableModel tableModel;
    private JTextField textProduktNavn;
    private JTextField textUdløbsdato;
    private JButton btnForsæt;
    private JComboBox<Integer> cageSelector;
    private HovedmenuUI hovedmenuUI;
    private JTextField textMængde;
    private JCheckBox checkBox;
    private JLabel expLabel;

    public RegisterProductUI(HovedmenuUI hovedmenuUI) throws DataAccessException {
        this.hovedmenuUI = hovedmenuUI;
        tableModel = new DefaultTableModel(new Object[] { "ProduktNavn", "ProduktID", "SkuNr" }, 0);
        productListModel.addAll(productController.findAll());
        cageListModel.addAll(cageController.findAll());
        initUI();
    }

    private void initUI() {
        setBounds(100, 100, 800, 600);
        getContentPane().setLayout(new BorderLayout());

        JPanel panel = new JPanel(new BorderLayout());
        JList<Product> productList = new JList<>(productListModel);
        productList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        productList.addListSelectionListener(e -> {
            Product p = productList.getSelectedValue();
            if (p != null) {
                textProduktNavn.setText(p.getProductName());
            }
        });
        panel.add(new JScrollPane(productList), BorderLayout.WEST);

        table = new JTable(tableModel);
        panel.add(new JScrollPane(table), BorderLayout.CENTER);
        getContentPane().add(panel, BorderLayout.CENTER);

        JPanel infoPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0; gbc.gridy = 0;
        infoPanel.add(new JLabel("Produktnavn:"), gbc);
        gbc.gridx = 1;
        textProduktNavn = new JTextField(15);
        infoPanel.add(textProduktNavn, gbc);

        gbc.gridx = 0; gbc.gridy++;
        infoPanel.add(new JLabel("Mængde:"), gbc);
        gbc.gridx = 1;
        textMængde = new JTextField(10);
        infoPanel.add(textMængde, gbc);

        gbc.gridx = 0; gbc.gridy++;
        infoPanel.add(new JLabel("BurNr:"), gbc);
        gbc.gridx = 1;
        cageSelector = new JComboBox<>();
        for (int i = 0; i < cageListModel.size(); i++) {
            cageSelector.addItem(cageListModel.getElementAt(i).getCageNo());
        }
        infoPanel.add(cageSelector, gbc);

        gbc.gridx = 0; gbc.gridy++;
        infoPanel.add(new JLabel("Har udløbsdato:"), gbc);
        gbc.gridx = 1;
        checkBox = new JCheckBox();
        infoPanel.add(checkBox, gbc);

        gbc.gridx = 0; gbc.gridy++;
        expLabel = new JLabel("Udløbsdato (yyyy-mm-dd):");
        expLabel.setVisible(false);
        infoPanel.add(expLabel, gbc);
        gbc.gridx = 1;
        textUdløbsdato = new JTextField(10);
        textUdløbsdato.setVisible(false);
        infoPanel.add(textUdløbsdato, gbc);

        checkBox.addActionListener(e -> {
            boolean vis = checkBox.isSelected();
            expLabel.setVisible(vis);
            textUdløbsdato.setVisible(vis);
            infoPanel.revalidate();
            infoPanel.repaint();
        });

        getContentPane().add(infoPanel, BorderLayout.NORTH);

        JPanel knapPanel = new JPanel();

        JButton btnTilføj = new JButton("Tilføj");
        btnTilføj.addActionListener(e -> {
            Product selected = productList.getSelectedValue();
            if (selected != null) {
                tableModel.addRow(new Object[] {
                    selected.getProductName(),
                    selected.getProductId(),
                    selected.getSkuNo()
                });
            }
        });
        knapPanel.add(btnTilføj);

        JButton btnSlet = new JButton("Slet");
        btnSlet.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                tableModel.removeRow(selectedRow);
            }
        });
        knapPanel.add(btnSlet);

        btnForsæt = new JButton("Forsæt");
        btnForsæt.addActionListener(e -> {
            try {
                RegisteredProductDB db = new RegisteredProductDB();
                for (int i = 0; i < tableModel.getRowCount(); i++) {
                    int productId = (int) tableModel.getValueAt(i, 1);
                    String input = textMængde.getText().trim();
                    if (input.isEmpty()) {
                        JOptionPane.showMessageDialog(this, "Indtast venligst en mængde.");
                        return;
                    }

                    int quantity = Integer.parseInt(input);
                    RegisteredProduct rp = new RegisteredProduct();
                    rp.setRegisteredid((int)(Math.random() * 100000));
                    rp.setProductid(productId);
                    rp.setCageNo((int) cageSelector.getSelectedItem());
                    rp.setQuantity(quantity);
                    rp.setRegistrationdate(new Date(System.currentTimeMillis()));

                    if (checkBox.isSelected()) {
                        String datoInput = textUdløbsdato.getText().trim();
                        if (!datoInput.isEmpty()) {
                            try {
                                Date exp = Date.valueOf(datoInput);
                                rp.setProductExp(exp);
                            } catch (IllegalArgumentException ex) {
                                JOptionPane.showMessageDialog(this, "Forkert datoformat. Brug yyyy-mm-dd.");
                                return;
                            }
                        } else {
                            JOptionPane.showMessageDialog(this, "Udfyld udløbsdato.");
                            return;
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "Udløbsdato kræves.");
                        return;
                    }

                    db.insert(rp);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            dispose();
            RBekræftetUI dialog = new RBekræftetUI(hovedmenuUI, tableModel);
            dialog.setVisible(true);
        });
        knapPanel.add(btnForsæt);

        JButton btnTilbage = new JButton("Tilbage");
        btnTilbage.addActionListener(e -> {
            dispose();
            hovedmenuUI.setVisible(true);
        });
        knapPanel.add(btnTilbage);

        getContentPane().add(knapPanel, BorderLayout.SOUTH);
    }

    public DefaultTableModel getTableModel() {
        return tableModel;
    }
}
