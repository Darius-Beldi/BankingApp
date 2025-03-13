package Cards;
import java.util.Random;

public class Card {

    private String IBAN;
    private String Number;
    private String CardName;
    private String Name;
    private Integer Month;
    private Integer Year;
    private Integer CVV;

    public Card(String _CardName, String _Name){
        IBAN = "RO";
        Random rand = new Random();
        for (int i = 0 ; i < 14 ; i++){
            int n = rand.nextInt(10);
            IBAN = IBAN + n;
        }

        Number = "";
        for (int i = 0 ; i < 14 ; i++){
            int n = rand.nextInt(10);
            Number = Number + n;
        }

        CardName = _CardName;
        Name = _Name;

        Month = rand.nextInt(13);
        Year = 25 + rand.nextInt(7);

        CVV = 0;
        for(int i = 1; i <= 3 ;i++){
            CVV += (10 ^ i) * rand.nextInt(10);
        }

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
}
