import Connection.AdressBookStatements;
import User.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdressBook extends AdressBookStatements {

    private static Integer generatedIdAdressBook;
    private Integer idAdressBook;
    private Integer idUser;
    private String name;
    private String IBAN;


    static {
        try {
            PreparedStatement selectStatement = c.prepareStatement("SELECT idAdressBook FROM AdressBooks ORDER BY idAdressBook DESC LIMIT 1");
            ResultSet rs = selectStatement.executeQuery();

            if (rs.next()) {
                generatedIdAdressBook = rs.getInt(1);
            } else {
                generatedIdAdressBook = 0; // No rows in the table
            }

            rs.close();
            selectStatement.close();
        } catch (SQLException e) {
            generatedIdAdressBook = 0;
            e.printStackTrace(); // Consider logging the exception
        }
    }

    public AdressBook(User _user, String _name, String _IBAN) {
        generatedIdAdressBook++;
        idAdressBook = generatedIdAdressBook;

        idUser = _user.getIdUser();
        name = _name;
        IBAN = _IBAN;

        insertIntoDatabase();
    }

    public AdressBook(Integer id, Integer idUser, String name, String iban) {
        this.idAdressBook = id;
        this.idUser = idUser;
        this.name = name;
        this.IBAN = iban;
    }

    private void insertIntoDatabase() {
        try {
            insertAdressBookStatement.setInt(1, idAdressBook);
            insertAdressBookStatement.setInt(2, idUser);
            insertAdressBookStatement.setString(3, name);
            insertAdressBookStatement.setString(4, IBAN);
            insertAdressBookStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getName() {
        return name;
    }
    public String getIBAN() {
        return IBAN;
    }
}
