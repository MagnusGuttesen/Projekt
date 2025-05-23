package ui;

import java.awt.EventQueue;



import javax.swing.JFrame;
import javax.swing.JTextField;

import controller.EmployeeController;
import db.DataAccessException;
import model.Employee;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.BorderLayout;
import javax.swing.JPanel;

public class LogIndUI  {

    private EmployeeController employeeController;
    private JFrame frame;
    private JTextField txtID;
    private JTextField txtPassword;
    private JLabel lblInfo;
    private JPanel panel;
    private JPanel panel_1;
    private JPanel imagePanel;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    LogIndUI window = new LogIndUI();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public LogIndUI() {
        initialize();
        try {
            employeeController = new EmployeeController();
        } catch (DataAccessException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Databaseforbindelse fejlede!", "Fejl", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(500, 200, 330, 200);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        imagePanel = new JPanel();
		
        JLabel label = new JLabel();
        label.setIcon(new ImageIcon(new ImageIcon("C:\\Users\\muuse\\Downloads\\Løvbjerg.png").getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH)));
        imagePanel.add(label, BorderLayout.CENTER);
        
        
		panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.NORTH);
		
		panel.add(imagePanel);

		panel_1 = new JPanel();
		panel.add(panel_1);
	

		JLabel lblID = new JLabel("MedarbejderID:");
		panel_1.add(lblID);
		lblID.setFont(new Font("Cambria", Font.BOLD, 15));

		txtID = new JTextField();
		panel_1.add(txtID);
		txtID.setFont(new Font("Cambria", Font.PLAIN, 15));
		
		
		JLabel lblPassword = new JLabel("Adgangskode:");
		panel_1.add(lblPassword);
		lblPassword.setFont(new Font("Cambria", Font.BOLD, 15));
		
		txtPassword = new JTextField();
		panel_1.add(txtPassword);
		txtPassword.setFont(new Font("Cambria", Font.PLAIN, 15));
		panel_1.setBorder(BorderFactory.createEmptyBorder(0,485,0,485));
		
		lblInfo = new JLabel("");
		lblInfo.setForeground(Color.RED);
		panel_1.add(lblInfo);

		JButton btnLogind = new JButton("Log ind");
		panel_1.add(btnLogind);
        btnLogind.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String enteredID = txtID.getText().trim();
                String enteredPassword = txtPassword.getText().trim();

                Employee employee = employeeController.findEmployeeById(enteredID);

                if (employee != null && employee.getPassword().equals(enteredPassword)) {
                    HovedmenuUI hovedmenuUI = new HovedmenuUI(LogIndUI.this);
                    frame.setVisible(false);
                   
                    hovedmenuUI.setVisible(true);
                } else {
                    lblInfo.setText("Ugyldigt ID eller adgangskode!");
                }
            }
        });
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        
        panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));
        frame.setVisible(true);
     
	
		
	}
}
