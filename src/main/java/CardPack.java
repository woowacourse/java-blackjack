import java.util.List;

public class CardPack {

    private static final int FIRST_CARD = 0;

    private final List<Card> cards;

    public CardPack(List<Card> cards) {
        this.cards = cards;
    }

    public Card pickOneCard() {
        return cards.remove(FIRST_CARD);
    }

    public boolean isUsedAll() {
        return cards.isEmpty();
    }
}
