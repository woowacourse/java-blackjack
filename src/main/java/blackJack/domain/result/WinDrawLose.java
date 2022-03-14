package blackJack.domain.result;

import blackJack.domain.participant.Dealer;
import blackJack.domain.participant.Player;

public enum WinDrawLose {
    WIN("승"),
    DRAW("무"),
    LOSE("패");

    private final String result;

    WinDrawLose(String result) {
        this.result = result;
    }

    public static WinDrawLose calculateWinDrawLose(Player player, Dealer dealer) {
        WinDrawLose resultByBust = getWinLoseByBust(player, dealer);
        if (resultByBust != null) return resultByBust;

        WinDrawLose resultByBlackJack = getWinDrawByBlackJack(player, dealer);
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
            return WIN;
        }
        if (getDrawByBlackJack(player, dealer)) {
            return DRAW;
        }
        return null;
    }

    private static boolean getWinByBlackJack(Player player, Dealer dealer) {
        return player.isBlackJack() && !dealer.isBlackJack();
    }

    private static boolean getDrawByBlackJack(Player player, Dealer dealer) {
        return player.isBlackJack() && dealer.isBlackJack();
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

    public String getResult() {
        return result;
    }
}
