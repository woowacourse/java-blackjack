import java.util.ArrayList;
import java.util.List;

public class Cards {

    private final List<Card> cards;

    private Cards(List<Card> cards) {
        this.cards = cards;
    }

    public static Cards empty() {
        return new Cards(new ArrayList<>());
    }

    public void add(Card card) {
        this.cards.add(card);
    }

    public List<Card> getCards() {
        return cards;
    }
}
