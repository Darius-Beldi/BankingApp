import Cards.Card;
import Connection.MenuStatements;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;
import User.User;
public class Menu extends MenuStatements {

    private static Integer idCurrentUser;

    static {
        idCurrentUser = -1;
    }

    public void menu() throws SQLException {
        while(true){
            if (chooseLorR()) //true= login , false = register
                try {
                    if (Login()) {
                        System.out.println("Login succesful");
                        mainPage();
                        return;
                    } else {
                        System.out.println("Login failed");
                    }
                } catch (SQLException | NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }
            else {
                Register();
            }
        }

    }

    ///  true = login, false = register
    public boolean chooseLorR(){
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
    /// true = login was succesful
    public static boolean Login() throws SQLException, NoSuchAlgorithmException {
        System.out.println("LOGIN");
        System.out.println("Email: ");
        String _email = new Scanner(System.in).nextLine();

        checkForExistingEmailStatement.setString(1, _email);
        ResultSet rs = checkForExistingEmailStatement.executeQuery();
        String foundEmail = "";
        if(rs.next()){
            foundEmail = rs.getString(1);
        }

        if(foundEmail.equals(_email)){
            boolean ok = false;
            for(int i = 3; i>=0; i--){
                System.out.println("Password: ");
                String _password = new Scanner(System.in).nextLine();
                getPasswordStatement.setString(1, _email);
                ResultSet password = getPasswordStatement.executeQuery();
                if(password.next()){
                    if(password.getString(1).equals(Crypt(_password))){

                        getIdStatement.setString(1, foundEmail);
                        ResultSet id = getIdStatement.executeQuery();
                        if(id.next()){
                            idCurrentUser = id.getInt(1);
                        }

                        return true;
                    }
                    else{
                        System.out.println("Wrong password. Attempts left: " + i);
                    }
                }
            }
            return ok;
        }
       return false;
    }

    public static void Register() throws SQLException {
        System.out.println("REGISTER");
        System.out.println("First Name: ");
        String _FirstName = new Scanner(System.in).nextLine();
        System.out.println("Last Name: ");
        String _LastName = new Scanner(System.in).nextLine();


        int[] _Birth_date;
        while(true){
            System.out.println("Birth Date: (yyyy-mm-dd)");
            try{
            _Birth_date = Arrays.stream(new Scanner(System.in).nextLine().split("-")).mapToInt(Integer::parseInt).toArray();
            }catch (Exception e){
                System.out.println("Invalid date format");
                continue;
            }
            break;

        }


        String _Email = "";
        while (true){
            System.out.println("Email: ");
            _Email = new Scanner(System.in).nextLine();

            checkForExistingEmailStatement.setString(1, _Email);
            ResultSet rs = checkForExistingEmailStatement.executeQuery();
            if(rs.next()){
                System.out.println("Email already in use");
            }
            else break;
        }


        System.out.println("Password: ");
        String _Password = new Scanner(System.in).nextLine();
        System.out.println("Confirm Password: ");
        String _ConfirmPassword = new Scanner(System.in).nextLine();
        if (_Password.equals(_ConfirmPassword)) {
            try {
                User user = new User(_FirstName, _LastName, new Date(_Birth_date[0] - 1900, _Birth_date[1], _Birth_date[2]), _Email, _Password, false);
                idCurrentUser = user.getIdUser();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }
    }
    public void mainPage() throws SQLException {
        System.out.println("MAIN PAGE");
        System.out.println("1. View Account");
        System.out.println("2. Transfer Money");
        System.out.println("3. View Transactions");
        System.out.println("4. Create a new Card");
        System.out.println("5. Logout");
        System.out.println("6. Exit");

        Scanner sc = new Scanner(System.in);
        while(true){
            String option = sc.nextLine();
            switch(option){
                case "1":
                    viewAccount();
                    break;
                case "2":
                    transferMoney();
                    break;
                case "3":
                    viewTransactions();
                    break;
                case "4":
                    createCard();
                case "5":
                    menu();
                    return;
                case "6":
                    System.exit(0);
                    break;
                default:
                    System.out.println("Wrong input. Please try again");
                    break;
            }
        }


    }

    private void createCard() throws SQLException {

        System.out.println("CREATE CARD");
        System.out.println("Card Name: ");
        String _CardName = new Scanner(System.in).nextLine();
        Card c = new Card(idCurrentUser, _CardName);
        return;
    }

    private void viewTransactions() {
    }

    private void transferMoney() {
    }

    private void viewAccount() throws SQLException {

        while(true){


            System.out.println("VIEW ACCOUNT");
            System.out.println("1. View Details");
            System.out.println("2. Change Password");
            System.out.println("3. See Cards");
            System.out.println("4. Back");
            Scanner sc = new Scanner(System.in);
            String option = sc.nextLine();
            switch(option){
                case "1":
                    viewDetails();
                    break;
                case "2":
                    changePassword();
                    break;
                case "3":
                    seeCards();
                    break;
                case "4":
                    mainPage();
                    return;
                default:
                    System.out.println("Wrong input. Please try again");
                    break;
            }
        }
    }

    private void seeCards() {

        System.out.println("CARDS");
        try {
            getCardsStatement.setInt(1, idCurrentUser);
            ResultSet rs = getCardsStatement.executeQuery();
            while(rs.next()) {
                System.out.println("Card Name: " + rs.getString(1));
                System.out.println("IBAN: " + rs.getString(2));
                System.out.println("Number: " + rs.getString(3));
                System.out.println("Name: " + rs.getString(4));
                System.out.println("Month: " + rs.getInt(5));
                System.out.println("Year: " + rs.getInt(6));
                System.out.println("CVV: " + rs.getInt(7));
                System.out.println("Balance: " + rs.getInt(8));
                System.out.println("\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void changePassword() {
        System.out.println("CHANGE PASSWORD");
        System.out.println("Old Password: ");
        String oldPassword = new Scanner(System.in).nextLine();
        System.out.println("New Password: ");
        String newPassword = new Scanner(System.in).nextLine();
        System.out.println("Confirm New Password: ");
        String confirmNewPassword = new Scanner(System.in).nextLine();
        if(newPassword.equals(confirmNewPassword)){
            try {

                getPasswordbyIDStatement.setInt(1, idCurrentUser);
                ResultSet rs = getPasswordbyIDStatement.executeQuery();
                if(rs.next()){
                    if(rs.getString(1).equals(Crypt(oldPassword))){
                        updatePasswordStatement.setString(1, Crypt(newPassword));
                        updatePasswordStatement.setInt(2, idCurrentUser);
                        updatePasswordStatement.execute();
                        return;
                    }
                    else{
                        System.out.println("Wrong password");
                        return;
                    }
                }


            } catch (SQLException | NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }
    }

    private void viewDetails() throws SQLException {
        System.out.println("VIEW DETAILS");
        try {
            getDetailsStatement.setInt(1, idCurrentUser);
            ResultSet rs = getDetailsStatement.executeQuery();
            if (rs.next()) {
                System.out.println("First Name: " + rs.getString(1));
                System.out.println("Last Name: " + rs.getString(2));
                System.out.println("Birth Date: " + rs.getDate(3));
                System.out.println("Email: " + rs.getString(4));
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }

        System.out.println("1. Back");
        Scanner sc = new Scanner(System.in);
        while(true){
            String option = sc.nextLine();
            switch(option){
                case "1":
                    viewAccount();
                    return;
                default:
                    System.out.println("Wrong input. Please try again");
                    break;
            }
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
