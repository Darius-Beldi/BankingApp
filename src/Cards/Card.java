package Cards;
import Connection.CardStatements;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

import User.User;
public class Card extends CardStatements {

    private static int generatedIdCard;
    private int idCard;
    private String IBAN;
    private String Number;
    private Integer idUser;
    private String CardName;
    private String Name;
    private Integer Month;
    private Integer Year;
    private Integer CVV;
    private Integer Balance;
    private Random rand = new Random();

    static {
        try {
            PreparedStatement selectStatement = c.prepareStatement("SELECT idCard FROM Cards ORDER BY idUser DESC LIMIT 1");
            ResultSet rs = selectStatement.executeQuery();

            if (rs.next()) {
                generatedIdCard = rs.getInt(1);
            } else {
                generatedIdCard = 0; // No rows in the table
            }

            rs.close();
            selectStatement.close();
        } catch (SQLException e) {
            generatedIdCard = 0;
            e.printStackTrace(); // Consider logging the exception
        }
    }

    /// New Card Generator
    public Card(Integer _idUser, String _CardName) throws SQLException {
        generatedIdCard += 1;
        CardName = _CardName;

        getUserFirstNameStatement.setInt(1, _idUser);
        ResultSet rs = getUserFirstNameStatement.executeQuery();
        rs.next();
        Name = rs.getString(1);

        getUserLastNameStatement.setInt(1, _idUser);
        ResultSet rs2 = getUserLastNameStatement.executeQuery();
        rs2.next();
        Name += rs2.getString(1);

        IBAN = generateIBAN();
        Number = generateNumber();
        Month = rand.nextInt(12) + 1;
        Year = 25 + rand.nextInt(7);
        CVV = generateCVV();
        Balance = 200;
        idCard = generatedIdCard;
        idUser = _idUser;
        insertIntoDatabase();
    }

    public Card(Integer id, Integer idUser, String name, String cardName, String iban, String number, Integer month, Integer year, Integer cvv, Integer balance) {

        idCard = id;
        this.idUser = idUser;
        Name = name;
        CardName = cardName;
        IBAN = iban;
        Number = number;
        Month = month;
        Year = year;
        CVV = cvv;
        Balance = balance;

    }


    private void insertIntoDatabase() {
        try{
            insertCardStatement.setInt(1, idCard);
            insertCardStatement.setInt(2, idUser);
            insertCardStatement.setString(3, Name);
            insertCardStatement.setString(4, IBAN);
            insertCardStatement.setString(5, Number);
            insertCardStatement.setInt(6, Month);
            insertCardStatement.setInt(7, Year);
            insertCardStatement.setInt(8, CVV);
            insertCardStatement.setInt(9, Balance);
            insertCardStatement.setString(10, CardName);
            insertCardStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private String generateIBAN() throws SQLException {

        while(true){
            IBAN = "RO";
            for (int i = 0 ; i < 21 ; i++){
                int n = rand.nextInt(10);
                IBAN = IBAN + n;
            }
            checkIBANStatement.setString(1, IBAN);
            ResultSet rs = checkIBANStatement.executeQuery();

            if (!rs.next()) {
                return IBAN;
            }
        }
    }
    private String generateNumber() throws SQLException {
        while(true){
            Number = "";
            for (int i = 0 ; i < 14 ; i++){
                int n = rand.nextInt(10);
                Number = Number + n;
            }
            checkCardNumbersStatement.setString(1, Number);
            ResultSet rs = checkCardNumbersStatement.executeQuery();
            if (!rs.next()) {
                return Number;
            }
        }

    }
    private Integer generateCVV(){
        StringBuilder temp = new StringBuilder();
        CVV = 0;
        for(int i = 1; i <= 3 ;i++){
            temp.append(rand.nextInt(9) + 1);
        }
        return Integer.parseInt(temp.toString());
    }



    public String getIBAN() {
        return IBAN;
    }

    public String getNumber() {
        return Number;
    }

    public String getCardName() {
        return CardName;
    }

    public String getName() {
        return Name;
    }

    public Integer getMonth() {
        return Month;
    }

    public Integer getYear() {
        return Year;
    }

    public Integer getCVV() {
        return CVV;
    }

    public void Afisare(){
        System.out.println("IBAN: " + IBAN);
        System.out.println("Card Name: " + CardName );
        System.out.println("Name: " + Name);
        System.out.println("Number: " + Number);
        System.out.println("Expiration Date: " + Month + "/" + Year);
        System.out.println("CVV: " + CVV);
        }

    public static int getGeneratedIdCard() {
        return generatedIdCard;
    }

    public int getIdCard() {
        return idCard;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public Integer getBalance() {
        return Balance;
    }

    public String ShowDetails(){
       String details = "";
        details += "IBAN: " + IBAN + "\n";
        details += "Card Name: " + CardName + "\n";
        details += "Name: " + Name + "\n";
        details += "Number: " + Number + "\n";
        details += "Expiration Date: " + Month + "/" + Year + "\n";
        details += "CVV: " + CVV + "\n";
        return details;
    }
}
