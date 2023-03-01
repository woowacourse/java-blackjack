import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Player {

    public static final int LIMIT_TAKE_CARD_VALUE = 21;
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

    public boolean checkCardsCondition() {
        int totalValue = 0;
        for (Card card : cards) {
            totalValue += card.getValue();
        }
        return totalValue <= LIMIT_TAKE_CARD_VALUE;
    }
}
