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

//Dialog til registrering af produkter i bure
public class RegisterProductUI extends JDialog {

    private int cageNo;
 // Controller klasser bruges til at hente og håndtere data fra databasen
    private ProductController productController = new ProductController();
    private CageController cageController = new CageController();
 // Modeller til at holde visninger af produkter og bure
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

 // Konstruktør initialiserer data og bygger brugerfladen
    public RegisterProductUI(HovedmenuUI hovedmenuUI) throws DataAccessException {
        this.hovedmenuUI = hovedmenuUI;
        
     // Tilføjer data til modellerne fra databasen
        tableModel = new DefaultTableModel(new Object[] { "ProduktNavn", "ProduktID", "SkuNr" }, 0);
        productListModel.addAll(productController.findAll());
        cageListModel.addAll(cageController.findAll());
        initUI(); // Bygger GUI
    }

    private void initUI() {
        setBounds(100, 100, 800, 600);
        getContentPane().setLayout(new BorderLayout());

     // Listepanel med produkter
        JPanel panel = new JPanel(new BorderLayout());
        JList<Product> productList = new JList<>(productListModel);
        productList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
     // Når et produkt vælges, vises dets navn i tekstfeltet
        productList.addListSelectionListener(e -> {
            Product p = productList.getSelectedValue();
            if (p != null) {
                textProduktNavn.setText(p.getProductName());
            }
        });
        panel.add(new JScrollPane(productList), BorderLayout.WEST);

     // Tabel der viser valgte produkter, som skal registreres
        table = new JTable(tableModel);
        panel.add(new JScrollPane(table), BorderLayout.CENTER);
        getContentPane().add(panel, BorderLayout.CENTER);

     // Panel til inputfelter (produktnavn, mængde, bur, dato)
        JPanel infoPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

     // Tilføj felter med labels for produkt, mængde og bur
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

     // Checkbox til aktivering af udløbsdato feltet
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

     // Viser/skjuler dato feltet afhængigt af checkbox
        checkBox.addActionListener(e -> {
            boolean vis = checkBox.isSelected();
            expLabel.setVisible(vis);
            textUdløbsdato.setVisible(vis);
            infoPanel.revalidate();
            infoPanel.repaint();
        });

        getContentPane().add(infoPanel, BorderLayout.NORTH);

     // Panel med knapper: Tilføj, Slet, Forsæt, Tilbage
        JPanel knapPanel = new JPanel();

     // Tilføjer valgte produkt til tabel
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

     // Sletter valgt række fra tabel
        JButton btnSlet = new JButton("Slet");
        btnSlet.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                tableModel.removeRow(selectedRow);
            }
        });
        knapPanel.add(btnSlet);

     // Forsætter og gemmer registrering i databasen
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
                    rp.setRegisteredid((int)(Math.random() * 100000)); // Midlertidig id generering
                    rp.setProductid(productId);
                    rp.setCageNo((int) cageSelector.getSelectedItem());
                    rp.setQuantity(quantity);
                    rp.setRegistrationdate(new Date(System.currentTimeMillis()));

                 // Validering og parsing af udløbsdato
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

                    db.insert(rp); // Indsætter registreret produkt i databasen
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }

         // Viser bekræftelsesdialog
            dispose();
            RBekræftetUI dialog = new RBekræftetUI(hovedmenuUI, tableModel);
            dialog.setVisible(true);
        });
        knapPanel.add(btnForsæt);

     // Lukker vinduet og vender tilbage til hovedmenu
        JButton btnTilbage = new JButton("Tilbage");
        btnTilbage.addActionListener(e -> {
            dispose();
            hovedmenuUI.setVisible(true);
        });
        knapPanel.add(btnTilbage);

        getContentPane().add(knapPanel, BorderLayout.SOUTH);
    }
    
 // Gør det muligt at hente model udefra (fx til tests)
    public DefaultTableModel getTableModel() {
        return tableModel;
    }
}
