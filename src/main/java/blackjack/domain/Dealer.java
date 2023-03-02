package blackjack.domain;

public class Dealer extends Participant {

    private static final String DEALER_NAME = "딜러";
    private static final int MAXIMUM_CARD_COUNT = 2;
    private static final int MAXIMUM_SCORE = 16;

    private final String name = DEALER_NAME;

    public Dealer(final Cards cards) {
        super(cards);
    }

    @Override
    public boolean isDrawable() {
        return isCardShortage() && isScoreLow();
    }

    private boolean isCardShortage() {
        return cards.count() <= MAXIMUM_CARD_COUNT;
    }

    private boolean isScoreLow() {
        return cards.calculateTotalScore() <= MAXIMUM_SCORE;
    }

    @Override
    public String getName() {
        return name;
    }
}
