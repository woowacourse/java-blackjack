package blackjack.domain.betting;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;

public enum BettingResult {

    BLACKJACK_WIN,
    WIN,
    PUSH,
    LOSE;

    public static BettingResult judge(Dealer dealer, Player player) {
        if (player.isBlackjack() && !dealer.isBlackjack()) {
            return BettingResult.BLACKJACK_WIN;
        }
        if (dealer.isBlackjack() && !player.isBlackjack()) {
            return BettingResult.LOSE;
        }
        return compareBust(player, dealer);
    }

    private static BettingResult compareBust(Player player, Dealer dealer) {
        if (player.isBust()) {
            return BettingResult.LOSE;
        }
        if (dealer.isBust()) {
            return BettingResult.WIN;
        }
        return compareScore(player, dealer);
    }

    private static BettingResult compareScore(Player player, Dealer dealer) {
        if (player.hasHigherScore(dealer)) {
            return BettingResult.WIN;
        }
        if (dealer.hasHigherScore(player)) {
            return BettingResult.LOSE;
        }
        return BettingResult.PUSH;
    }
}
