package Connection;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MenuStatements extends ConnectionString {

    public static String checkForExistingEmail = "SELECT email FROM users WHERE email = ?";
    public static PreparedStatement checkForExistingEmailStatement;
    public static String getPassword = "SELECT password FROM users WHERE email = ?";
    public static PreparedStatement getPasswordStatement;
    public static String getId = "SELECT iduser FROM users WHERE email = ?";
    public static PreparedStatement getIdStatement;
    public static String getDetails = "SELECT firstname, lastname, birthdate, email FROM users WHERE iduser = ?";
//     System.out.println("First Name: " + rs.getString(1));
//                System.out.println("Last Name: " + rs.getString(2));
//                System.out.println("Birth Date: " + rs.getDate(3));
//                System.out.println("Email: " + rs.getString(4));
    public static PreparedStatement getDetailsStatement;

    static {
        try{
            checkForExistingEmailStatement = c.prepareStatement(checkForExistingEmail);
            getPasswordStatement = c.prepareStatement(getPassword);
            getIdStatement = c.prepareStatement(getId);
            getDetailsStatement = c.prepareStatement(getDetails);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
