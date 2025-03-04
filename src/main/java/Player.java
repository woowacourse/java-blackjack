import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Player {

    private final String name;
    private final Cards cards = new Cards();

    public Player(String name) {
        this.name = name;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public List<Card> getCards() {
        return cards.getCards();
    }

    public boolean isBust() {
        return cards.sum() > 21;
    }
}
