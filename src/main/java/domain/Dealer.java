package domain;

public class Dealer extends Participant {
    private static final String DEFAULT_NAME = "딜러";
    private static final int DEALER_MIN_SCORE_POLICY = 17;

    private final CardDeck cardDeck;

    public Dealer(CardDeck cardDeck) {
        super(DEFAULT_NAME);
        this.cardDeck = new CardDeck();
    }

    public Card pickCard() {
        return cardDeck.draw();
    }

    public boolean isNecessaryMoreCard() {
        return calculateScore() < DEALER_MIN_SCORE_POLICY;
    }

    public void shuffle() {
        cardDeck.shuffle();
    }
}
