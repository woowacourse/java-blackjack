import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Player {

    private final Name name;
    private final List<Card> cards;

    public Player(final Name name) {
        this.name = name;
        this.cards = new ArrayList<>();
    }

    public void takeCard(Card card) {
        cards.add(card);
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
}
