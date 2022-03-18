package blackjack.domain.result;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;

public enum BlackjackMatch {

    WIN_BLACKJACK("승", 1.5),
    WIN("승", 1),
    DRAW("무", 0),
    LOSE("패", -1),
    LOSE_BLACK_JACK("패", -1.5);

    private final String result;
    private final double profitRatio;

    BlackjackMatch(String result, double profitRatio) {
        this.result = result;
        this.profitRatio = profitRatio;
    }

    public static BlackjackMatch calculateMatch(Player player, Dealer dealer) {
        if (player.isBust() || dealer.isBust()) {
            return getWinLoseByBust(player);
        }
        if (player.isBlackjack() || dealer.isBlackjack()) {
            return getWinDrawByBlackjack(player, dealer);
        }
        return getWinDrawLoseByScore(player, dealer);
    }

    private static BlackjackMatch getWinLoseByBust(Player player) {
        if (player.isBust()) {
            return LOSE;
        }
        return WIN;
    }

    private static BlackjackMatch getWinDrawByBlackjack(Player player, Dealer dealer) {
        if (isWinBlackjack(player, dealer)) {
            return WIN_BLACKJACK;
        }
        if (isDrawBlackjack(player, dealer)) {
            return DRAW;
        }
        return LOSE_BLACK_JACK;
    }

    private static boolean isWinBlackjack(Player player, Dealer dealer) {
        return player.isBlackjack() && !dealer.isBlackjack();
    }

    private static boolean isDrawBlackjack(Player player, Dealer dealer) {
        return player.isBlackjack() && dealer.isBlackjack();
    }

    private static boolean isLoseBlackjack(Player player, Dealer dealer) {
        return !player.isBlackjack() && dealer.isBlackjack();
    }

    private static BlackjackMatch getWinDrawLoseByScore(Player player, Dealer dealer) {
        if (player.getScore() > dealer.getScore()) {
            return WIN;
        }
        if (player.getScore() == dealer.getScore()) {
            return DRAW;
        }
        return LOSE;
    }

    public BlackjackMatch swapResult() {
        if (Math.abs(profitRatio) == WIN_BLACKJACK.profitRatio) {
            return swapResultBlackjack();
        }
        if (profitRatio == WIN.profitRatio) {
            return LOSE;
        }
        if (profitRatio == LOSE.profitRatio) {
            return WIN;
        }
        return DRAW;
    }

    private BlackjackMatch swapResultBlackjack() {
        if (profitRatio == WIN_BLACKJACK.profitRatio) {
            return LOSE_BLACK_JACK;
        }
        return WIN_BLACKJACK;
    }

    public double getProfitRatio() {
        return profitRatio;
    }

    public String getResult() {
        return result;
    }
}
