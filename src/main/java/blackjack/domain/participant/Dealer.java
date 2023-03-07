package blackjack.domain.participant;

import blackjack.domain.card.Cards;
import blackjack.domain.result.Result;

public class Dealer extends Participant {

    private static final String DEALER_NAME = "딜러";
    private static final int MAXIMUM_CARD_COUNT = 2;
    private static final int MAXIMUM_SCORE = 16;

    private final String name = DEALER_NAME;

    public Dealer() {
        super();
    }

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

    public Result compareScoreTo(Player player) {
        int dealerScore = this.getScore();
        int playerScore = player.getScore();

        if (playerScore > 21) {
            return Result.WIN;
        }
        if (dealerScore > 21) {
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
        return name;
    }
}
