package blackjack.domain;

public class Dealer {

    private static final String DEALER_NAME = "딜러";
    private static final int MAXIMUM_CARD_COUNT = 2;
    private static final int MAXIMUM_SCORE = 16;

    private final String name = DEALER_NAME;
    private final Cards cards;

    public Dealer(final Cards cards) {
        this.cards = cards;
    }

    public void hit(final Card card) {
        cards.addCard(card);
    }

    public boolean isHittable() {
        return isCardShortage() && isScoreLow();
    }

    private boolean isCardShortage() {
        return cards.count() <= MAXIMUM_CARD_COUNT;
    }

    private boolean isScoreLow() {
        return cards.calculateTotalScore() <= MAXIMUM_SCORE;
    }

    public int getScore() {
        return cards.calculateTotalScore();
    }

    public String getName() {
        return name;
    }

    public Cards getCards() {
        return cards;
    }
}
