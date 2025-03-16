import Cards.Card;
import Cards.ChildPlan;
import Cards.BasicPlan;
import Cards.ProPlan;
import java.util.*;


public class  User {

    private String FirstName;
    private String LastName;
    private Set<Card> Cards = new HashSet();
    private Date BirthDate;
    private String Email;
    private String Password;
    private Map<User, String> AdressBook = new HashMap<>();
    // AdressBook.put( Nume, IBAN   )

    public User(String _FirstName, String _LastName, Date _BirthDate){
        FirstName = _FirstName;
        LastName = _LastName;
        BirthDate = _BirthDate;
    }

    ///Creates a new card for the user
    public void AddCard(String _CardName){
        Card c = new Card(_CardName, FirstName + LastName);
        Cards.add(c);
    }

    //sa se faca check inainte sa fie chemata functia asta
    public void AddAdress(User _User, String _IBAN) {
        this.AdressBook.put(_User, _IBAN );
    }

}
