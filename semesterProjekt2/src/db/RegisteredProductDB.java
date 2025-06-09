package db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.RegisteredProduct;

/*Klassen håndterer databaseoperationer for RegisteredProduct objekter.
  Den implementerer RegisteredProductDBIF og giver funktionen til at gemme og hente registrerede produkter.*/
public class RegisteredProductDB implements RegisteredProductDBIF {

    private static final String INSERT_Q = 
        "INSERT INTO RegisteredProduct (registeredid, productid, cageno, quantity, registrationdate, productexp) VALUES (?, ?, ?, ?, ?, ?)";
    private PreparedStatement insertPS;

    public RegisteredProductDB() throws DataAccessException {
        init();
    }

    private void init() throws DataAccessException {
        try {
            Connection con = DBConnection.getInstance().getConnection();
            insertPS = con.prepareStatement(INSERT_Q);
        } catch (SQLException e) {
            throw new DataAccessException("Kunne ikke forberede insertPS", e);
        }
    }

 // Henter alle registrerede produkter for et bestemt burnummer
    @Override
    public List<RegisteredProduct> findByCageNo(int cageNo) {
        List<RegisteredProduct> list = new ArrayList<>();

        try {
            Connection conn = DBConnection.getInstance().getConnection();

            String sql = "SELECT rp.*, p.productname FROM RegisteredProduct rp " +
                         "JOIN Product p ON rp.productid = p.productid " +
                         "WHERE rp.cageNo = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, cageNo);
            ResultSet rs = ps.executeQuery();

         // Gennemløber resultatet og opbygger RegisteredProduct objekter
            while (rs.next()) {
                RegisteredProduct p = new RegisteredProduct();
                p.setProductName(rs.getString("productname"));
                p.setQuantity(rs.getInt("quantity"));
                p.setCageNo(rs.getInt("cageno"));
                p.setProductExp(rs.getDate("productexp"));
                p.setRegistrationdate(rs.getDate("registrationdate"));
                list.add(p);
            }
        } catch (SQLException | DataAccessException e) {
            e.printStackTrace();
        }

        return list;
    }
 // Indsætter et nyt registeret produkt objekt i databasen
    @Override
    public void insert(RegisteredProduct rp) throws SQLException {
        insertPS.setInt(1, rp.getRegisteredid());
        insertPS.setInt(2, rp.getProductid());
        insertPS.setInt(3, rp.getCageNo());
        insertPS.setInt(4, rp.getQuantity());
        insertPS.setDate(5, rp.getRegistrationdate());
        insertPS.setDate(6, rp.getProductExp());
        insertPS.executeUpdate();
    }
}
