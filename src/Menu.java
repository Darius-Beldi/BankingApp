import Connection.ConnectionString;
import Connection.MenuStatements;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Menu extends MenuStatements {

    public void displayMenu() throws SQLException {

        boolean login = StartUp();
        if(login){
            Login();
        }else{
            Register();
        }

    }
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

    public static void Login() throws SQLException {
        System.out.println("Login");
        System.out.println("Email: ");
        String _email = new Scanner(System.in).nextLine();

        checkForExistingEmailStatement.setString(1, _email);
        ResultSet rs = checkForExistingEmailStatement.executeQuery();

        if(rs.next()){
            String foundEmail = rs.getString(1);
        }

        System.out.println("Password: ");
        String password = new Scanner(System.in).nextLine();



    }

    public static void Register(){
        System.out.println("register");
    }
}
