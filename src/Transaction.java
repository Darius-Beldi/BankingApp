import Connection.ConnectionString;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class Transaction extends ConnectionString {
    private static Integer generatedIdTransaction;
    private Integer idTransaction;
    private Integer idCard;
    private Date date;
    private Double amount;

    static {
        try {
            PreparedStatement selectStatement = c.prepareStatement("SELECT idTransaction FROM Transactions ORDER BY idTransaction DESC LIMIT 1");
            ResultSet rs = selectStatement.executeQuery();

            if (rs.next()) {
                generatedIdTransaction = rs.getInt(1);
            } else {
                generatedIdTransaction = 0; // No rows in the table
            }

            rs.close();
            selectStatement.close();
        } catch (SQLException e) {
            generatedIdTransaction = 0;
            e.printStackTrace(); // Consider logging the exception
        }
    }

    public Transaction(int _idCard, Double _amount) {
        generatedIdTransaction++;
        idTransaction = generatedIdTransaction;
        idCard = _idCard;
        date = new Date(System.currentTimeMillis());
        amount = _amount;
        insertIntoDatabase();

    }

    private void insertIntoDatabase() {

    }

}
