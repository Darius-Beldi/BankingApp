package Connection;

import com.sun.jdi.connect.spi.Connection;

import java.sql.PreparedStatement;

public class TransactionStatements extends ConnectionString {

    protected static String insertTransaction = "insert into transactions " +
            "(idtransaction, idcard, amount, date, type)" +
            " values (?, ?, ?, ?, ?)";

    protected static String selectTransaction = "select * from transactions where idcard = ?";

    protected static PreparedStatement insertTransactionStatement;
    protected static PreparedStatement selectTransactionStatement;

    static {
        try {
            insertTransactionStatement = c.prepareStatement(insertTransaction);
            selectTransactionStatement = c.prepareStatement(selectTransaction);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
