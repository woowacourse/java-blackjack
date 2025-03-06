package blackjack.domain;

import static blackjack.domain.BlackjackConstants.BUST_THRESHOLD;

import blackjack.domain.card.Cards;

public class WinnerDecider {

    public WinningResult decideWinning(Cards mainCards, Cards subCards) {
        int mainScore = mainCards.calculateMaxScore();
        int subScore = subCards.calculateMaxScore();

        if (mainScore > BUST_THRESHOLD.getSymbol() && subScore > BUST_THRESHOLD.getSymbol()) {
            return WinningResult.DRAW;
        }

        if (mainScore > BUST_THRESHOLD.getSymbol()) {
            return WinningResult.LOSE;
        }

        if (subScore > BUST_THRESHOLD.getSymbol()) {
            return WinningResult.WIN;
        }

        if (mainCards.isBlackjack() && !subCards.isBlackjack()) {
            return WinningResult.WIN;
        }

        if (subCards.isBlackjack() && !mainCards.isBlackjack()) {
            return WinningResult.LOSE;
        }

        if (mainScore == subScore) {
            return WinningResult.DRAW;
        }

        if (mainScore > subScore) {
            return WinningResult.WIN;
        }

        return WinningResult.LOSE;
    }
}
