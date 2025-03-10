package domain;

public class Dealer extends Gamer {
    public static final int BUST_THRESHOLD = 16;

    private final Deck deck;

    public Dealer(Deck deck) {
        super();
        this.deck = deck;
    }

    public boolean isDrawable() {
        return this.isDrawable(BUST_THRESHOLD);
    }

    public Card drawCard() {
        return deck.draw();
    }

    public Card showAnyOneCard() {
        Cards dealerCard = getCards();
        return dealerCard.getFirst();
    }
}
