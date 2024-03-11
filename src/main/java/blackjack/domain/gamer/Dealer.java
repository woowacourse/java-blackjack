package blackjack.domain.gamer;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hand;
import blackjack.domain.game.GameResult;

public class Dealer extends Player {
    public static final int HIT_UPPER_BOUND = 16;
    private static final int FIRST_DEAL_CARD_INDEX = 0;
    private static final String DEALER_NAME = "딜러";

    public Dealer(Hand hand) {
        super(DEALER_NAME, hand);
    }

    public boolean isScoreUnderBound() {
        return hand.findMaximumScore() <= HIT_UPPER_BOUND;
    }

    public GameResult judgeGameResult(Player player) {
        if (player.isBust()) {
            return GameResult.LOSE;
        }
        if (isPlayerWin(player)) {
            return GameResult.WIN;
        }
        if (hand.compareScoreTo(player.hand) == 0) {
            return GameResult.PUSH;
        }
        return GameResult.LOSE;
    }

    private boolean isPlayerWin(Player player) {
        return hand.compareScoreTo(player.hand) == 1 ||
                player.isBlackjack() ||
                isBust();
    }

    public Card getOneDealCard() {
        return hand.getMyCardAt(FIRST_DEAL_CARD_INDEX);
    }
}
