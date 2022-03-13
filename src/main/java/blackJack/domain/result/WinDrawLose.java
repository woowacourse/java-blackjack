package blackJack.domain.result;

import blackJack.domain.participant.Dealer;
import blackJack.domain.participant.Participant;
import blackJack.domain.participant.Player;

public enum WinDrawLose {
    WIN("승"),
    DRAW("무"),
    LOSE("패");

    private static final int BLACK_JACK = 21;
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
        if (isBust(player)) {
            return LOSE;
        }
        if (isBust(dealer)) {
            return WIN;
        }
        return null;
    }

    private static boolean isBust(Participant participant) {
        return participant.calculateFinalScore() > BLACK_JACK;
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
        if (player.calculateFinalScore() > dealer.calculateFinalScore()) {
            return WIN;
        }
        if (player.calculateFinalScore() == dealer.calculateFinalScore()) {
            return DRAW;
        }
        return LOSE;
    }

    public String getResult() {
        return result;
    }
}
