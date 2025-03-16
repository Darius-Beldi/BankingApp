package Cards;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Card {

    private String IBAN;
    private String Number;
    private String CardName;
    private String Name;
    private Integer Month;
    private Integer Year;
    private Integer CVV;
    private static Set<String>  cardNumbers = new HashSet<String>();
    private static Set<String> IBANNumbers = new HashSet<String>();

    /// New Card Generator
    public Card(String _CardName, String _Name){
        Random rand = new Random();


        while (true){
            IBAN = "RO";
            for (int i = 0 ; i < 14 ; i++){
                int n = rand.nextInt(10);
                IBAN = IBAN + n;
            }

            if(!IBANNumbers.contains(IBAN)){
                break;
            }
        }

        while(true){
            Number = "";
            for (int i = 0 ; i < 14 ; i++){
                int n = rand.nextInt(10);
                Number = Number + n;
            }

            if(!cardNumbers.contains(Number)){
                break;
            }
        }


        CardName = _CardName;
        Name = _Name;

        Month = rand.nextInt(12) + 1;
        Year = 25 + rand.nextInt(7);

        StringBuilder temp = new StringBuilder();
        CVV = 0;
        for(int i = 1; i <= 3 ;i++){
            temp.append(rand.nextInt(9) + 1);
        }
        CVV =  Integer.parseInt(temp.toString());

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
