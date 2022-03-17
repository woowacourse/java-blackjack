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

    public static double calculateProfitRatio(Player player, Dealer dealer) {
        final BlackjackMatch resultByBust = getWinLoseByBust(player, dealer);
        if (resultByBust != null) return resultByBust.profitRatio;

        final BlackjackMatch resultByBlackjack = getWinDrawByBlackjack(player, dealer);
        if (resultByBlackjack != null) return resultByBlackjack.profitRatio;

        return getWinDrawLoseByScore(player, dealer).profitRatio;
    }

    public static BlackjackMatch calculateMatch(Player player, Dealer dealer) {
        final BlackjackMatch resultByBust = getWinLoseByBust(player, dealer);
        if (resultByBust != null) return resultByBust;

        final BlackjackMatch resultByBlackjack = getWinDrawByBlackjack(player, dealer);
        if (resultByBlackjack != null) return resultByBlackjack;

        return getWinDrawLoseByScore(player, dealer);
    }

    private static BlackjackMatch getWinLoseByBust(Player player, Dealer dealer) {
        if (player.isBust()) {
            return LOSE;
        }
        if (dealer.isBust()) {
            return WIN;
        }
        return null;
    }

    private static BlackjackMatch getWinDrawByBlackjack(Player player, Dealer dealer) {
        if (getWinByBlackjack(player, dealer)) {
            return WIN_BLACKJACK;
        }
        if (getDrawByBlackjack(player, dealer)) {
            return DRAW;
        }
        if (getLoseByBlackjack(player, dealer)) {
            return LOSE_BLACK_JACK;
        }
        return null;
    }

    private static boolean getWinByBlackjack(Player player, Dealer dealer) {
        return player.isBlackjack() && !dealer.isBlackjack();
    }

    private static boolean getDrawByBlackjack(Player player, Dealer dealer) {
        return player.isBlackjack() && dealer.isBlackjack();
    }

    private static boolean getLoseByBlackjack(Player player, Dealer dealer) {
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

    public static BlackjackMatch swapResult(BlackjackMatch result) {
        BlackjackMatch swapBlackjackResult = swapResultBlackJack(result);
        if (swapBlackjackResult != null) return swapBlackjackResult;

        if (result == WIN) {
            return LOSE;
        }
        if (result == LOSE) {
            return WIN;
        }
        return DRAW;
    }

    private static BlackjackMatch swapResultBlackJack(BlackjackMatch result) {
        if (result == WIN_BLACKJACK) {
            return LOSE_BLACK_JACK;
        }
        if (result == LOSE_BLACK_JACK) {
            return WIN_BLACKJACK;
        }
        return null;
    }

    public double getProfitRatio() {
        return profitRatio;
    }

    public String getResult() {
        return result;
    }
}
