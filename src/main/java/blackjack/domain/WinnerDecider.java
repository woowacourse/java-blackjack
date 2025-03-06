package blackjack.domain;

import blackjack.domain.participants.Dealer;
import blackjack.domain.participants.Player;

public class WinnerDecider {

    private final Dealer dealer;
    public static final int MAX_SCORE = 21;

    public WinnerDecider(Dealer dealer) {
        this.dealer = dealer;
    }

    public WinningResult decidePlayerWinning(Player player) {
        int dealerScore = dealer.calculateMaxScore();
        int playerScore = player.calculateMaxScore();

        if (playerScore > MAX_SCORE) {
            return WinningResult.LOSE;
        }

        if (dealer.isBlackjack() && !player.isBlackjack()) {
            return WinningResult.LOSE;
        }

        if (dealerScore > MAX_SCORE) {
            return WinningResult.WIN;
        }

        if (dealerScore == playerScore) {
            return WinningResult.DRAW;
        }
        if (dealerScore > playerScore) {
            return WinningResult.LOSE;
        }
        return WinningResult.WIN;
    }

    public WinningResult decideDealerWinning(Player player) {
        WinningResult winningResult = decidePlayerWinning(player);
        if (winningResult == WinningResult.WIN) {
            return WinningResult.LOSE;
        }
        if (winningResult == WinningResult.LOSE) {
            return WinningResult.WIN;
        }
        return WinningResult.DRAW;
    }


}
