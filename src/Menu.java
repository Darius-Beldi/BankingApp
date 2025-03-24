import Cards.Card;
import Connection.MenuStatements;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import User.User;
public class Menu extends MenuStatements {

    private static Integer idCurrentUser;
    private static User currentUser;
    private static Set<Card> cards;

    static {
        idCurrentUser = -1;
        cards = new HashSet<>();
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

                        getUserStatement.setString(1, foundEmail);
                        ResultSet rsUser = getUserStatement.executeQuery();
                        if(rsUser.next()){
                            int id = rsUser.getInt(1);
                            String firstName = rsUser.getString(2);
                            String lastName = rsUser.getString(3);
                            Date birthDate = rsUser.getDate(4);
                            String email = rsUser.getString(5);
                            String password1 = rsUser.getString(6);
                            currentUser = new User(id, firstName, lastName, birthDate, email, password1, true);
                            System.out.println(password1);
                            }

                        getCardsStatement.setInt(1, currentUser.getIdUser());
                        ResultSet rsCards = getCardsStatement.executeQuery();
                        while(rsCards.next()){
                            Integer id = rsCards.getInt(1);
                            Integer idUser = rsCards.getInt(2);
                            String Name = rsCards.getString(3);
                            String cardName = rsCards.getString(4);
                            String IBAN = rsCards.getString(5);
                            String Number = rsCards.getString(6);
                            Integer Month = rsCards.getInt(7);
                            Integer Year = rsCards.getInt(8);
                            Integer CVV = rsCards.getInt(9);
                            Integer Balance = rsCards.getInt(10);

                            Card c = new Card(id, idUser, Name, cardName, IBAN, Number, Month, Year, CVV, Balance);

                            cards.add(c);
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


        int[] _Birth_datearray;
        Date _Birth_date;
        while(true){
            System.out.println("Birth Date: (yyyy-mm-dd)");
            try{
            _Birth_datearray = Arrays.stream(new Scanner(System.in).nextLine().split("-")).mapToInt(Integer::parseInt).toArray();
            if(_Birth_datearray[1] >= 12 || _Birth_datearray[1] <= 1 || _Birth_datearray[2] >= 31 || _Birth_datearray[2] <= 1){
                System.out.println("Invalid date format");
                continue;
            }

            _Birth_date = new Date(_Birth_datearray[0] - 1900, _Birth_datearray[1]-1, _Birth_datearray[2]-1);
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
                currentUser = new User(0,_FirstName, _LastName, _Birth_date, _Email, _Password, false);

            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }
    }
    public void mainPage() throws SQLException {
        while(true){
            System.out.println("MAIN PAGE");
            System.out.println("1. View Account");
            System.out.println("2. Transfer Money");
            System.out.println("3. View Transactions");
            System.out.println("4. Create a new Card");
            System.out.println("5. Logout");
            System.out.println("6. Exit");

            Scanner sc = new Scanner(System.in);

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
                        break;
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
        Card c = new Card(currentUser.getIdUser(), _CardName);
        cards.add(c);
        System.out.println("Card created succesfully");
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
        for(Card c : cards) {
            System.out.println("Card Name: " + c.getCardName());
            System.out.println("IBAN: " + c.getIBAN());
            System.out.println("Number: " + c.getNumber());
            System.out.println("Name: " + c.getName());
            System.out.println("Expiration Date: " + c.getMonth() + "/" + c.getYear());
            System.out.println("CVV: " + c.getCVV());
            System.out.println("Balance: " + c.getBalance());
            System.out.println("\n");
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


                    if(currentUser.getPassword().equals(Crypt(oldPassword))){
                        updatePasswordStatement.setString(1, Crypt(newPassword));
                        updatePasswordStatement.setInt(2, currentUser.getIdUser());
                        updatePasswordStatement.execute();
                        currentUser.setPassword(Crypt(newPassword));
                        System.out.println("Password changed succesfully");
                        return;
                    }
                    else{
                        System.out.println("Wrong password");
                        return;
                    }



            } catch (SQLException | NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }
    }

    private void viewDetails() throws SQLException {
        System.out.println("VIEW DETAILS");


        System.out.println("First Name: " + currentUser.getFirstName());
        System.out.println("Last Name: " + currentUser.getLastName());
        System.out.println("Birth Date: " + currentUser.getBirthDate());
        System.out.println("Email: " + currentUser.getEmail());



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
