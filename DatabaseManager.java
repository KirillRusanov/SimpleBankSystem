package BankSystem;

import java.sql.*;

public class DatabaseManager {

    private String url = "";

    public DatabaseManager(String url) {
        this.url = "jdbc:sqlite:" + url;
    }


    private Connection connect() {

        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }


    public void insert(String number, String pin) {
        String sql = "INSERT INTO card (number, pin) VALUES(?, ?)";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, number);
            pstmt.setString(2, pin);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    public String getPinAccount(String number) {
        String sql = "SELECT pin "
                + "FROM card WHERE number = ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, number);

            ResultSet rs = pstmt.executeQuery();

            return rs.getString("pin");


        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return "Incorrect Account";
        }
    }


    public int getBalanceAccount(String number) {
        String sql = "SELECT balance "
                + "FROM card WHERE number = ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, number);

            ResultSet rs = pstmt.executeQuery();

            return rs.getInt("balance");


        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return -1;
        }
    }


    public void closeAccount(String number) {
        String sql = "DELETE FROM card WHERE number = ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // set the corresponding param
            pstmt.setString(1, number);
            // execute the delete statement
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    public void addIncome(String number, int money) {
        String sql = "UPDATE card SET balance = " + getBalanceAccount(number) + " +  ? "
                + "WHERE number = ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // set the corresponding param
            pstmt.setInt(1, money);
            pstmt.setString(2, number);
            // update
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    public void doTransfer(String fromNumber, String toNumber, int money) {
        String sql = "UPDATE card SET balance = " + getBalanceAccount(fromNumber) + " -  ? "
                + "WHERE number = ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // set the corresponding param
            pstmt.setInt(1, money);
            pstmt.setString(2, fromNumber);
            // update
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        String sql2 = "UPDATE card SET balance = " + getBalanceAccount(toNumber) + " +  ? "
                + "WHERE number = ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql2)) {

            // set the corresponding param
            pstmt.setInt(1, money);
            pstmt.setString(2, toNumber);
            // update
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    public boolean checkExist(String number) {
        boolean isUserExists = false;
        String sql = "SELECT *"
                + "FROM card WHERE number = ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // set the corresponding param
            pstmt.setString(1, number);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    isUserExists = true;
                }
            }

            return isUserExists;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return isUserExists;
    }
}
