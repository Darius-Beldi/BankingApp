package Connection;


import java.sql.PreparedStatement;

public class CardStatements extends ConnectionString {

    protected static String insertCard = "insert into cards " +
            "(idcard, iduser, name, iban, cardnumber, month, year, cvv, balance, cardname)" +
            " values (?, ?, ?, ?, ?, ?, ?,?, ?, ?)";

    protected static String selectCard = "select * from cards where iduser = ?";

    protected static String checkIBANs = "SELECT 1 FROM cards WHERE IBAN = ?";
    protected static String checkCardNumbers = "SELECT 1 FROM cards WHERE cardnumber = ?";
    protected static String getUserFirstName = "SELECT Firstname FROM users WHERE iduser = ?";
    protected static String getUserLastName = "SELECT lastname FROM users WHERE iduser = ?";

    protected static PreparedStatement insertCardStatement;
    protected static PreparedStatement selectCardStatement;
    protected static PreparedStatement checkIBANStatement;
    protected static PreparedStatement checkCardNumbersStatement;
    protected static PreparedStatement getUserFirstNameStatement;
    protected static PreparedStatement getUserLastNameStatement;


    static {
        try {
            insertCardStatement = c.prepareStatement(insertCard);
            selectCardStatement = c.prepareStatement(selectCard);
            checkIBANStatement = c.prepareStatement(checkIBANs);
            checkCardNumbersStatement = c.prepareStatement(checkCardNumbers);
            getUserFirstNameStatement = c.prepareStatement(getUserFirstName);
            getUserLastNameStatement = c.prepareStatement(getUserLastName);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
