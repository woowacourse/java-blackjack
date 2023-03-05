package blackjack.domain.participant;

import blackjack.domain.card.Cards;

public class Dealer extends Participant {

    private static final String NAME = "딜러";
    private static final int MAXIMUM_DRAWABLE_SCORE = 16;
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
    public boolean isDealer() {
        return true;
    }

    @Override
    public String getName() {
        return name;
    }

    public Result showResult(final int score) {
        final int dealerScore = getScore();
        if (score > BLACK_JACK_SCORE) {
            return Result.LOSE;
        }
        if (dealerScore > BLACK_JACK_SCORE || score > dealerScore) {
            return Result.WIN;
        }
        if (score < dealerScore) {
            return Result.LOSE;
        }
        return Result.DRAW;
    }

    public int getMaximumDrawableScore() {
        return MAXIMUM_DRAWABLE_SCORE;
    }
}
