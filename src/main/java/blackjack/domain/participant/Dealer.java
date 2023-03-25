package blackjack.domain.participant;

import blackjack.domain.card.Cards;
import blackjack.domain.result.Result;

public class Dealer extends Participant {

    private static final String DEALER_NAME = "딜러";
    private static final int MAXIMUM_CARD_COUNT = 2;
    private static final int THRESHOLD_SCORE = 16;
    private static final int MAXIMUM_SCORE = 21;

    public Dealer() {
        super();
    }

    public Dealer(final Cards cards) {
        super(cards);
    }

    @Override
    public boolean isDrawable() {
        return isCardShortage() && isScoreLow() && drawable;
    }

    private boolean isCardShortage() {
        return cards.count() <= MAXIMUM_CARD_COUNT;
    }

    private boolean isScoreLow() {
        return cards.calculateTotalScore() <= THRESHOLD_SCORE;
    }

    public Result compareScoreTo(final Player player) {
        final int dealerScore = this.getScore();
        final int playerScore = player.getScore();

        if (playerScore > MAXIMUM_SCORE) {
            return Result.WIN;
        }
        if (dealerScore > MAXIMUM_SCORE) {
            return Result.LOSE;
        }
        if (dealerScore > playerScore) {
            return Result.WIN;
        }
        if (dealerScore < playerScore) {
            return Result.LOSE;
        }
        return Result.DRAW;
    }

    @Override
    public String getName() {
        return DEALER_NAME;
    }

    public int getThresholdScore() {
        return THRESHOLD_SCORE;
    }
}
