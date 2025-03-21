package Connection;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MenuStatements extends ConnectionString {

    public static String checkForExistingEmail = "SELECT email FROM users WHERE email = ?";
    public static String getDetails = "SELECT firstname, lastname, birthdate, email FROM users WHERE iduser = ?";
    public static String getPassword = "SELECT password FROM users WHERE email = ?";
    public static String getId = "SELECT iduser FROM users WHERE email = ?";
    public static String getPasswordbyID = "SELECT password FROM users WHERE iduser = ?";
    public static String updatePassword = "UPDATE users SET password = ? WHERE iduser = ?";

    public static PreparedStatement checkForExistingEmailStatement;
    public static PreparedStatement getPasswordStatement;
    public static PreparedStatement getIdStatement;
    public static PreparedStatement getDetailsStatement;
    public static PreparedStatement getPasswordbyIDStatement;
    public static PreparedStatement updatePasswordStatement;

    static {
        try{
            checkForExistingEmailStatement = c.prepareStatement(checkForExistingEmail);
            getPasswordStatement = c.prepareStatement(getPassword);
            getIdStatement = c.prepareStatement(getId);
            getDetailsStatement = c.prepareStatement(getDetails);
            getPasswordbyIDStatement = c.prepareStatement(getPasswordbyID);
            updatePasswordStatement = c.prepareStatement(updatePassword);


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
