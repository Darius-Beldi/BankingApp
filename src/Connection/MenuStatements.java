package Connection;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MenuStatements extends ConnectionString {

    protected static String checkForExistingEmail = "SELECT email FROM users WHERE email = ?";
    protected static PreparedStatement checkForExistingEmailStatement;

    static {
        try{
            PreparedStatement checkForExistingEmailStatement = c.prepareStatement(checkForExistingEmail);



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
