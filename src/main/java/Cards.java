import java.util.List;

public class Cards {

    private static final int FIRST_CARD = 0;

    private final List<Card> cards;

    public Cards(List<Card> cards) {
        this.cards = cards;
    }

    public Card pickOneCard() {
        return cards.remove(FIRST_CARD);
    }

    public boolean isUsedAll() {
        return cards.isEmpty();
    }
}
