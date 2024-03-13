package blackjack.model.referee;

import blackjack.model.dealer.Dealer;
import blackjack.model.player.Player;

public enum MatchResult {
    BLACKJACK_WIN,
    WIN,
    LOSE,
    PUSH;

    public static MatchResult determine(final Dealer dealer, final Player player) {
        if (dealer.isBlackJack() && player.isBlackJack()) {
            return MatchResult.PUSH;
        }
        if (dealer.isBlackJack() && !player.isBlackJack()) {
            return MatchResult.LOSE;
        }
        if (!dealer.isBlackJack() && player.isBlackJack()) {
            return MatchResult.BLACKJACK_WIN;
        }
        return determineByTotalScore(dealer, player);
    }

    private static MatchResult determineByTotalScore(final Dealer dealer, final Player player) {
        int playerTotal = player.calculateCardsTotalScore();
        int dealerTotal = dealer.calculateCardsTotalScore();
        if (player.isBust()) {
            return MatchResult.LOSE;
        }
        if ((dealer.isBust() || playerTotal > dealerTotal)) {
            return MatchResult.WIN;
        }
        if (playerTotal == dealerTotal) {
            return MatchResult.PUSH;
        }
        return MatchResult.LOSE;
    }
}
