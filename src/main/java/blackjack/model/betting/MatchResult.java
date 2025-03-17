package blackjack.model.betting;

import blackjack.model.participant.Dealer;
import blackjack.model.participant.Player;

public enum MatchResult {

    BLACKJACK(1.5),
    WIN(1),
    LOSE(-1),
    DRAW(0),
    ;
    private final double profitRate;

    MatchResult(double profitRate) {
        this.profitRate = profitRate;
    }

    public static MatchResult calculatePlayerResult(Dealer dealer, Player player) {
        if (player.isBust()) {
            return LOSE;
        }
        if (dealer.isBust()) {
            return WIN;
        }
        if (isBothBlackjack(player, dealer)) {
            return DRAW;
        }
        if (player.isBlackjack()) {
            return BLACKJACK;
        }
        if (dealer.isBlackjack()) {
            return LOSE;
        }
        return determineByCompareTotal(player.calculateHandTotal(), dealer.calculateHandTotal());
    }

    private static boolean isBothBlackjack(Player player, Dealer dealer) {
        return player.isBlackjack() && dealer.isBlackjack();
    }

    private static MatchResult determineByCompareTotal(int playerTotal, int dealerTotal) {
        if (playerTotal > dealerTotal) {
            return WIN;
        }
        if (playerTotal < dealerTotal) {
            return LOSE;
        }
        return DRAW;
    }

    public static MatchResult reverse(MatchResult matchResult) {
        if (matchResult == WIN || matchResult == BLACKJACK) {
            return LOSE;
        }
        if (matchResult == LOSE) {
            return WIN;
        }
        return DRAW;
    }

    public double getProfitRate() {
        return profitRate;
    }
}
