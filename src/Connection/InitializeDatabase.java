package Connection;

public class InitializeDatabase extends ConnectionString{

    public static String createUsersTable = "CREATE TABLE IF NOT EXISTS users (\n"
            + "iduser INT AUTO_INCREMENT PRIMARY KEY,\n"
            + "firstname VARCHAR(45) NOT NULL,\n"
            + "lastname VARCHAR(45) NOT NULL,\n"
            + "birthdate DATE NOT NULL,\n"
            + "email VARCHAR(45) NOT NULL,\n"
            + "password VARCHAR(45) NOT NULL\n"
            + ");";

    public static String createTransactionsTable = "CREATE TABLE IF NOT EXISTS transactions (\n"
            + "idtransaction INT AUTO_INCREMENT PRIMARY KEY,\n"
            + "idcardoutgoing INT NOT NULL,\n"
            + "idcardincoming INT NOT NULL,\n"
            + "amount DECIMAL(10,2) NOT NULL,\n"
            + "date DATE NOT NULL,\n"
            + "FOREIGN KEY (idcardoutgoing) REFERENCES cards(idcard),\n"
            + "FOREIGN KEY (idcardincoming) REFERENCES cards(idcard)\n"
            + ");";

    public static String createCardsTable = "CREATE TABLE IF NOT EXISTS cards (\n"
            + "idcard INT AUTO_INCREMENT PRIMARY KEY,\n"
            + "iduser INT NOT NULL,\n"
            + "name VARCHAR(45) NOT NULL,\n"
            + "iban VARCHAR(45) NOT NULL,\n"
            + "cardnumber VARCHAR(45) NOT NULL,\n"
            + "month INT NOT NULL,\n"
            + "year INT NOT NULL,\n"
            + "cvv INT NOT NULL,\n"
            + "balance DECIMAL(10,2) NOT NULL,\n"
            + "cardname VARCHAR(45) NOT NULL,\n"
            + "FOREIGN KEY (idUser) REFERENCES Users(idUser)\n"
            + ");";
    public static String createAdressBooksTable = "CREATE TABLE IF NOT EXISTS adressbooks (\n"
            + "idadressbook INT AUTO_INCREMENT PRIMARY KEY,\n"
            + "iduser INT NOT NULL,\n"
            + "name VARCHAR(45) NOT NULL,\n"
            + "iban VARCHAR(45) NOT NULL,\n"
            + "FOREIGN KEY (idUser) REFERENCES Users(idUser)"
            + ");";


    static{
        try {
            c.createStatement().executeUpdate(createUsersTable);
            c.createStatement().executeUpdate(createCardsTable);
            c.createStatement().executeUpdate(createTransactionsTable);
            c.createStatement().executeUpdate(createAdressBooksTable);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
