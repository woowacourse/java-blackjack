package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hand;
import java.util.List;

public class Dealer extends Participant {

    private static final String NAME = "딜러";
    private static final int MAXIMUM_DRAWABLE_SCORE = 16;

    public Dealer() {
        super();
    }

    @Override
    public boolean isDrawable() {
        return isDrawableCardCount() && isDrawableScore();
    }

    private boolean isDrawableCardCount() {
        return hand.count() <= BLACK_JACK_CARD_COUNT;
    }

    private boolean isDrawableScore() {
        return hand.calculateTotalScore() <= MAXIMUM_DRAWABLE_SCORE;
    }

    public String getName() {
        return NAME;
    }

    @Override
    public boolean isDealer() {
        return true;
    }

    public Result showResult(final int score) {
        final int dealerScore = getScore();
        if (score > BLACK_JACK_SCORE) {
            return Result.LOSE;
        }
        if (dealerScore > BLACK_JACK_SCORE || dealerScore < score) {
            return Result.WIN;
        }
        if (dealerScore > score) {
            return Result.LOSE;
        }
        return Result.DRAW;
    }

    public Hand getHiddenHand() {
        final List<Card> cards = getHand().getCards();
        return new Hand(cards.subList(0, cards.size() - 1));
    }

    public int getMaximumDrawableScore() {
        return MAXIMUM_DRAWABLE_SCORE;
    }
}
