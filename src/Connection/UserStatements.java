package Connection;

import java.sql.PreparedStatement;

public class UserStatements extends ConnectionString{
    protected static String insertUser = "insert into users " +
            "(iduser, firstname, lastname, birthdate, email, password)" +
            " values (?, ?, ?, ?, ?, ?)";
    protected static String selectUser = "select * from users where username = ?";

    protected static PreparedStatement insertUserStatement;
    protected static PreparedStatement selectUserStatement;

    {
        try {
            insertUserStatement = c.prepareStatement(insertUser);
            selectUserStatement = c.prepareStatement(selectUser);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }





}
