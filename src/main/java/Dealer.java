import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Dealer {
    private final String name;
    private final List<Card> cards;

    public Dealer() {
        this.name = "딜러";
        this.cards = new ArrayList<>();
    }

    public void takeCard(Card card) {
        cards.add(card);
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
}
