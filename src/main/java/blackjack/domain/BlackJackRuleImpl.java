package blackjack.domain;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;

public class BlackJackRuleImpl implements BlackJackRule {

    private static final int BUST_POINT = 21;

    @Override
    public ResultType calculateDealerResult(final Dealer dealer, final Player player) {
        final int dealerScore = dealer.currentScore();
        final int playerScore = player.currentScore();
        if (isTie(dealerScore, playerScore)) {
            return ResultType.TIE;
        }
        if (isDealerWin(dealerScore, playerScore)) {
            return ResultType.WIN;
        }
        return ResultType.LOSE;
    }

    private boolean isTie(final int dealerScore, final int playerScore) {
        if (playerScore > BUST_POINT && dealerScore > BUST_POINT) {
            return true;
        }
        return playerScore == dealerScore;
    }

    private boolean isDealerWin(final int dealerScore, final int playerScore) {
        if (playerScore > BUST_POINT) {
            return true;
        }
        if (dealerScore > BUST_POINT) {
            return false;
        }
        return dealerScore > playerScore;
    }
}
