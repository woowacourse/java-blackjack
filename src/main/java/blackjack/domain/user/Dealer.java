package blackjack.domain.user;

import blackjack.domain.GameResult;
import blackjack.domain.Score;
import blackjack.domain.card.Card;
import blackjack.domain.card.CardGroup;

import java.util.List;

public class Dealer extends User {

    public static final String DEALER_NAME_CODE = "dealer";
    private static final int DEALER_DRAW_SCORE_VALUE_LIMIT = 16;

    public Dealer(CardGroup initialGroup) {
        super(DEALER_NAME_CODE, initialGroup);
    }

    @Override
    public List<Card> getInitialHoldingCards() {
        return List.of(getHandholdingCards().get(0));
    }

    public boolean isOverDraw() {
        return this.getScore().getValue() > DEALER_DRAW_SCORE_VALUE_LIMIT;
    }

    public GameResult judgePlayerGameResult(Score playerScore) {
        if (this.getScore().isBust() || playerScore.isBust()) {
            return compareByBustCondition(playerScore);
        }
        return compareByScore(playerScore);
    }

    private GameResult compareByBustCondition(final Score playerScore) {
        if (playerScore.isBust()) {
            return GameResult.LOSE;
        }
        return GameResult.WIN;
    }

    private GameResult compareByScore(final Score playerScore) {
        final Score dealerScore = this.getScore();
        if (dealerScore.getValue() < playerScore.getValue()) {
            return GameResult.WIN;
        }
        if (dealerScore.getValue() > playerScore.getValue()) {
            return GameResult.LOSE;
        }
        if (dealerScore.isMaxNumber()) {
            return compareByBlackJackCondition(dealerScore, playerScore);
        }
        return GameResult.TIE;
    }

    private GameResult compareByBlackJackCondition(final Score dealerScore, final Score playerScore) {
        if (dealerScore.isBlackJack() && !playerScore.isBlackJack()) {
            return GameResult.LOSE;
        }
        if (!dealerScore.isBlackJack() && playerScore.isBlackJack()) {
            return GameResult.WIN;
        }
        return GameResult.TIE;
    }
}
