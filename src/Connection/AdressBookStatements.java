package Connection;

import java.sql.PreparedStatement;

public class AdressBookStatements extends ConnectionString{

    private static String insertAdressBook = "insert into adressbooks (idadressbook, iduser, name, iban) values (?, ?, ?, ?)";
    protected static PreparedStatement insertAdressBookStatement;


    static{
        try{
            insertAdressBookStatement = c.prepareStatement(insertAdressBook);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
