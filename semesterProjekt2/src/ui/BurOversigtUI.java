package ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import controller.CageController;
import controller.RegisteredProductController;
import db.DataAccessException;
import model.Cage;
import model.RegisteredProduct;

import java.awt.*;
import java.util.List;

/*Brugergrænseflade til visning af produkter i et specifikt bur.
 Viser en liste over bure, og når et bur vælges, vises tilknyttede produkter i en tabel.*/
public class BurOversigtUI extends JFrame {

    private DefaultListModel<Cage> cageListModel = new DefaultListModel<>();
    private CageController cageController;
    private JComboBox<Integer> cageSelector;
    private JTable productTable;
    private DefaultTableModel tableModel;

    
      /*Opretter brugergrænsefladen for oversigt over burene.
      Viser burene i en dropdown(rullemenu med bur numre) og en tabel med produkter.*/
     
    public BurOversigtUI(HovedmenuUI hovedmenuUI) {
        setTitle("Bur oversigt");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Henter alle bure fra databasen og tilføjer til modellen
        try {
            cageController = new CageController();
            cageListModel.addAll(cageController.findAll());
        } catch (DataAccessException e) {
            e.printStackTrace();
        }

        // Dropdown med bur nummer
        cageSelector = new JComboBox<>();
        for (int i = 1; i <= cageListModel.getSize(); i++) {
            cageSelector.addItem(i);
        }

        // Når brugeren vælger et bur, vis produkter for det bur
        cageSelector.addActionListener(e -> {
            int selectedCage = (int) cageSelector.getSelectedItem();
            visProdukterIFraBur(selectedCage);
        });

        // Tabelmodel til visning af produktnavn, antal og udløbsdato
        tableModel = new DefaultTableModel(new String[]{"Produktnavn", "Antal", "Udløbsdato"}, 0);
        productTable = new JTable(tableModel);

        // Tilbage knap lukker vinduet og viser hovedmenuen igen
        JButton btnTilbage = new JButton("Tilbage");
        btnTilbage.addActionListener(e -> {
            this.dispose();
            hovedmenuUI.setVisible(true);
        });

        add(cageSelector, BorderLayout.NORTH);
        add(new JScrollPane(productTable), BorderLayout.CENTER);
        add(btnTilbage, BorderLayout.SOUTH);
    }

    //Henter og viser alle produkter fra det valgte bur i tabellen.
     
    private void visProdukterIFraBur(int cageNo) {
        tableModel.setRowCount(0); // Nulstiller tabellen før visning af nye data

        List<RegisteredProduct> produkter = RegisteredProductController.getInstance().findByCageNo(cageNo);
        for (RegisteredProduct p : produkter) {
            tableModel.addRow(new Object[]{
                p.getProductName(),
                p.getQuantity(),
                p.getProductExp()
            });
        }
    }
}
