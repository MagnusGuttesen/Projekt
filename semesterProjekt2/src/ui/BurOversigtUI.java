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

public class BurOversigtUI extends JFrame {
	private DefaultListModel<Cage> cageListModel = new DefaultListModel<>();
	private CageController cageController;
    private JComboBox<Integer> cageSelector;
    private JTable productTable;
    private DefaultTableModel tableModel;
   
    
  

    public BurOversigtUI(HovedmenuUI hovedmenuUI) {
        setTitle("Bur oversigt");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        
        try {
            cageController = new CageController();
            cageListModel.addAll(cageController.findAll());
        } catch (DataAccessException e) {
            e.printStackTrace();
        }


        cageSelector = new JComboBox<>();
        for (int i = 1; i <= cageListModel.getSize(); i++)  {
        	cageSelector.addItem(i);
        	
        	
        }
        
        cageSelector.addActionListener(e -> {
            int selectedCage = (int) cageSelector.getSelectedItem();
            visProdukterIFraBur(selectedCage);
           
        });

        tableModel = new DefaultTableModel(new String[]{"Produktnavn", "Antal", "Udløbsdato"}, 0);
        productTable = new JTable(tableModel);

        JButton btnTilbage = new JButton("Tilbage");
        btnTilbage.addActionListener(e -> {
            this.dispose();
            hovedmenuUI.setVisible(true);
        });

        add(cageSelector, BorderLayout.NORTH);
        add(new JScrollPane(productTable), BorderLayout.CENTER);
        add(btnTilbage, BorderLayout.SOUTH);
    }

    private void visProdukterIFraBur(int cageNo) {
        tableModel.setRowCount(0); // ryd visning

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
