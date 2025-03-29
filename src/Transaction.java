import Connection.ConnectionString;
import Connection.TransactionStatements;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class Transaction extends TransactionStatements {
    private static Integer generatedIdTransaction;
    private Integer idTransaction;
    private Integer idCardOutgoing;
    private Integer idCardIncoming;
    private Date date;
    private Integer amount;

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

    public Transaction(int _idCardOutgoing, int _idCardIncoming, Integer _amount) throws SQLException {

        generatedIdTransaction++;
        idTransaction = generatedIdTransaction;
        idCardOutgoing = _idCardOutgoing;
        idCardIncoming = _idCardIncoming;
        amount = _amount;
        date = new Date();
        insertIntoDatabase();

    }

    public Transaction(int _idTransaction, int _idCardOutgoing, int _idCardIncoming, Integer _amount, Date _date) {
        idTransaction = _idTransaction;
        idCardOutgoing = _idCardOutgoing;
        idCardIncoming = _idCardIncoming;
        amount = _amount;
        date = _date;

    }

    private void insertIntoDatabase() throws SQLException {
        insertTransactionStatement.setInt(1, idTransaction);
        insertTransactionStatement.setInt(2, idCardOutgoing);
        insertTransactionStatement.setInt(3, idCardIncoming);
        insertTransactionStatement.setDouble(4, amount);
        insertTransactionStatement.setDate(5, new java.sql.Date(date.getTime()));
        insertTransactionStatement.execute();
        return;
    }

}
