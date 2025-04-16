package Connection;

import java.sql.PreparedStatement;

public class UserStatements extends ConnectionString{
    protected static String insertUser = "insert into users " +
            "(iduser, firstname, lastname, birthdate, email, password)" +
            " values (?, ?, ?, ?, ?, ?)";
    public static PreparedStatement insertUserStatement;
    protected static PreparedStatement selectUserStatement;

    {
        try {
            insertUserStatement = c.prepareStatement(insertUser);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }





}
