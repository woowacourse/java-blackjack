package blackjack.domain.participant;

import blackjack.domain.card.Cards;

public class Dealer extends Participant {

    public static final int MAXIMUM_DRAWABLE_SCORE = 16;
    private static final String NAME = "딜러";
    private static final int MAXIMUM_DRAWABLE_CARD_COUNT = 2;

    private final String name = NAME;

    public Dealer() {
        super();
    }

    public Dealer(final Cards cards) {
        super(cards);
    }

    @Override
    public boolean isDrawable() {
        return isDrawableCardCount() && isDrawableScore();
    }

    private boolean isDrawableCardCount() {
        return cards.count() <= MAXIMUM_DRAWABLE_CARD_COUNT;
    }

    private boolean isDrawableScore() {
        return cards.calculateTotalScore() <= MAXIMUM_DRAWABLE_SCORE;
    }

    @Override
    public String getName() {
        return name;
    }
}
