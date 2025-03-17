import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.Scanner;

public class Functions {

    ///  true = login, false = register
    public boolean StartUp(){
        System.out.println("Welcome to Banking App");
        System.out.println("Do you already have an account?");
        System.out.println("Y/N: ");
        boolean ok = true;
        Scanner sc = new Scanner(System.in);
        while(ok==true){
            String HasAnAccount = sc.nextLine();
            switch(HasAnAccount){

                case "Y":
                    return true;

                case "N":
                    return false;

                case "y":
                    return true;

                case "n":
                    return false;


                default:
                    System.out.println("Wrong input. Please try again");
                    System.out.println("Y/N: ");
                    break;
            }
        }
        return true;
    }

    public static void Login(){
        System.out.println("login");
    }

    public static void Register(){
        System.out.println("register");
    }

    public static User[] LoadUsers(String fileLocation) throws FileNotFoundException {
        User[] users = null;
        users = new User[100];
        try {
            FileReader input = new FileReader(fileLocation);
            BufferedReader br = new BufferedReader(input);
            String myLine = null;
            int count = 0;
            while ((myLine = br.readLine()) != null) {
                String[] data = myLine.split("/");
                for(int i = 0; i<data.length; i++){
                    users[count] = new User(data[0], data[1], new Date( Integer.parseInt(data[2]),  Integer.parseInt(data[3]),  Integer.parseInt(data[4])), data[5], data[6]);

                }
                count++;
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println(e);
            ;
        }
        return users;
    }


    public static void LoadDataBase() {
        String url = "jdbc:mysql://localhost:3306";
        String username = "root";
        String password  = "parola";
        String query = "CREATE table test (id int, name varchar(255))";

        try {

            Class.forName("com.mysql.jdbc.Driver");
            // Establish connection
            Connection c = DriverManager.getConnection(
                    url, username, password);

            // Create a statement
            Statement st = c.createStatement();

            // Execute the query
            int count = st.executeUpdate(query);
            System.out.println(
                    "Number of rows affected by this query: "
                            + count);

            // Close the connection
            st.close();
            c.close();
            System.out.println("Connection closed.");
        }
        catch (SQLException e) {
            System.err.println("SQL Error: "
                    + e.getMessage());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


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


}
