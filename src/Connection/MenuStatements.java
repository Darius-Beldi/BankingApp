package Connection;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MenuStatements extends ConnectionString {

    public static String checkForExistingEmail = "SELECT email FROM users WHERE email = ?";
    public static String getDetails = "SELECT firstname, lastname, birthdate, email FROM users WHERE iduser = ?";
    public static String getPassword = "SELECT password FROM users WHERE email = ?";
    public static String getId = "SELECT iduser FROM users WHERE email = ?";
    public static String getUser = "SELECT * FROM users WHERE email = ?";
    public static String getPasswordbyID = "SELECT password FROM users WHERE iduser = ?";
    public static String updatePassword = "UPDATE users SET password = ? WHERE iduser = ?";
    public static String getCards = "SELECT * FROM cards WHERE iduser = ?";


    public static PreparedStatement checkForExistingEmailStatement;
    public static PreparedStatement getPasswordStatement;
    public static PreparedStatement getIdStatement;
    public static PreparedStatement getUserStatement;

    public static PreparedStatement getDetailsStatement;
    public static PreparedStatement getPasswordbyIDStatement;
    public static PreparedStatement updatePasswordStatement;
    public static PreparedStatement getCardsStatement;

    static {
        try{
            checkForExistingEmailStatement = c.prepareStatement(checkForExistingEmail);
            getPasswordStatement = c.prepareStatement(getPassword);
            getIdStatement = c.prepareStatement(getId);
            getUserStatement = c.prepareStatement(getUser);
            getDetailsStatement = c.prepareStatement(getDetails);
            getPasswordbyIDStatement = c.prepareStatement(getPasswordbyID);
            updatePasswordStatement = c.prepareStatement(updatePassword);
            getCardsStatement = c.prepareStatement(getCards);


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
