package User;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import Cards.Card;
import Connection.*;

public class  User extends UserStatements{

    private static Integer generatedIdUser;
    private Integer idUser;
    private String FirstName;
    private String LastName;
    private Date BirthDate;
    private String Email;
    private String Password;

    static {
        try {
            PreparedStatement selectStatement = c.prepareStatement("SELECT idUser FROM Users ORDER BY idUser DESC LIMIT 1");
            ResultSet rs = selectStatement.executeQuery();

            if (rs.next()) {
                generatedIdUser = rs.getInt(1);
            } else {
                generatedIdUser = 0; // No rows in the table
            }

            rs.close();
            selectStatement.close();
        } catch (SQLException e) {
            generatedIdUser = 0;
            e.printStackTrace(); // Consider logging the exception
        }
    }
    public User(String _FirstName, String _LastName, Date _BirthDate, String _Email, String _Password, Boolean alreadyInDatabase) throws NoSuchAlgorithmException {
        generatedIdUser++;
        idUser = generatedIdUser;
        FirstName = _FirstName;
        LastName = _LastName;
        BirthDate = _BirthDate;
        Email = _Email;
        Password = Crypt(_Password);
       if(!alreadyInDatabase)
            AddToDatabase();
    }




    private void AddToDatabase(){
        try {
            insertUserStatement.setInt(1, idUser);
            insertUserStatement.setString(2, FirstName);
            insertUserStatement.setString(3, LastName);
            java.sql.Date sqlDate = new java.sql.Date(BirthDate.getTime());


            insertUserStatement.setDate(4, sqlDate);
            insertUserStatement.setString(5, Email);
            insertUserStatement.setString(6, Password);
            insertUserStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void ShowDetails(){
        System.out.println("First Name: " + FirstName);
        System.out.println("Last Name: " + LastName);
        System.out.println("Birth Date: " + BirthDate);
        System.out.println("Email: " + Email);
        System.out.println("Password: " + Password);
    }

    public static String Crypt(String input) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(input.getBytes());
        byte[] digest = md.digest();

        // Convert byte array to hexadecimal string
        StringBuilder hexString = new StringBuilder();
        for (byte b : digest) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }

        return hexString.toString();
    }


    public  Integer getIdUser() {
        return idUser;
    }

    public String getFirstName() {
        return FirstName;
    }

    public String getLastName() {
        return LastName;
    }

    public Date getBirthDate() {
        return BirthDate;
    }

    public String getEmail() {
        return Email;
    }

    public String getPassword() {
        return Password;
    }
}
