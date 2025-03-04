import java.util.List;

public abstract class Gamer {

    protected final Cards cards = new Cards();

    public void addCard(Card card) {
        cards.add(card);
    }

    public List<Card> getCards() {
        return cards.getCards();
    }

    public boolean isBust() {
        return cards.sum() > 21;
    }

    public abstract boolean canReceiveAdditionalCards();

    public int getSumOfCards() {
        return cards.sum();
    }
}
