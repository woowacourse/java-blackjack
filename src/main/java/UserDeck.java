import java.util.ArrayList;
import java.util.List;

public class UserDeck {
    List<Card> list=new ArrayList<>();

    public void pushCard(Card card){
        list.add(card);
    }
}
