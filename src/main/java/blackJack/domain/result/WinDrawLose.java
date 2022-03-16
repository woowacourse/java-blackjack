package blackJack.domain.result;

import blackJack.domain.participant.Dealer;
import blackJack.domain.participant.Player;

public enum WinDrawLose {

    WIN_BLACKJACK("승", 1.5),
    WIN("승", 1),
    DRAW("무", 0),
    LOSE("패", -1),
    LOSE_BLACK_JACK("패", -1.5);

    private final String result;
    private final double profitRatio;

    WinDrawLose(String result, double profitRatio) {
        this.result = result;
        this.profitRatio = profitRatio;
    }

    public static double calculateProfitRatio(Player player, Dealer dealer) {
        final WinDrawLose resultByBust = getWinLoseByBust(player, dealer);
        if (resultByBust != null) return resultByBust.profitRatio;

        final WinDrawLose resultByBlackJack = getWinDrawByBlackJack(player, dealer);
        if (resultByBlackJack != null) return resultByBlackJack.profitRatio;

        return getWinDrawLoseByScore(player, dealer).profitRatio;
    }

    public static WinDrawLose calculateWinDrawLose(Player player, Dealer dealer) {
        final WinDrawLose resultByBust = getWinLoseByBust(player, dealer);
        if (resultByBust != null) return resultByBust;

        final WinDrawLose resultByBlackJack = getWinDrawByBlackJack(player, dealer);
        if (resultByBlackJack != null) return resultByBlackJack;

        return getWinDrawLoseByScore(player, dealer);
    }

    private static WinDrawLose getWinLoseByBust(Player player, Dealer dealer) {
        if (player.isBust()) {
            return LOSE;
        }
        if (dealer.isBust()) {
            return WIN;
        }
        return null;
    }

    private static WinDrawLose getWinDrawByBlackJack(Player player, Dealer dealer) {
        if (getWinByBlackJack(player, dealer)) {
            return WIN_BLACKJACK;
        }
        if (getDrawByBlackJack(player, dealer)) {
            return DRAW;
        }
        if (getLoseByBlackJack(player, dealer)) {
            return LOSE_BLACK_JACK;
        }
        return null;
    }

    private static boolean getWinByBlackJack(Player player, Dealer dealer) {
        return player.isBlackJack() && !dealer.isBlackJack();
    }

    private static boolean getDrawByBlackJack(Player player, Dealer dealer) {
        return player.isBlackJack() && dealer.isBlackJack();
    }

    private static boolean getLoseByBlackJack(Player player, Dealer dealer) {
        return !player.isBlackJack() && dealer.isBlackJack();
    }

    private static WinDrawLose getWinDrawLoseByScore(Player player, Dealer dealer) {
        if (player.getScore() > dealer.getScore()) {
            return WIN;
        }
        if (player.getScore() == dealer.getScore()) {
            return DRAW;
        }
        return LOSE;
    }

    public static WinDrawLose swapResult(WinDrawLose result) {
        WinDrawLose swapBlackJackResult = swapResultBlackJack(result);
        if (swapBlackJackResult != null) return swapBlackJackResult;

        if (result == WIN) {
            return LOSE;
        }
        if (result == LOSE) {
            return WIN;
        }
        return DRAW;
    }

    private static WinDrawLose swapResultBlackJack(WinDrawLose result) {
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
