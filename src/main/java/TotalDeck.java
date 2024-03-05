import java.util.ArrayList;
import java.util.List;

public class TotalDeck {
    Card card = new Card();
    List<Card> list = List.of(card);
    public Card getNewCard() {
        return list.get(0);
    }
}
