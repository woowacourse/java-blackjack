package domain.result;

import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.Players;

public enum WinOrLose {
    WIN("승"),
    DRAW("무"),
    LOSE("패");

    private final String result;

    WinOrLose(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }

    public static WinOrLose compareWinOrLose(Dealer dealer, Player player) {
        if (dealer.isBlackJack()) {
            return compareAtDealerBlackJack(player.isBlackJack());
        }
        if (player.isBust()) {
            return WinOrLose.LOSE;
        }
        if (player.isBlackJack() || dealer.isBust()) {
            return WinOrLose.WIN;
        }
        return judgeWinOrLose(player.calculateBestScore(), dealer.calculateBestScore());
    }

    private static WinOrLose compareAtDealerBlackJack(boolean isBlackJack) {
        if (isBlackJack) {
            return WinOrLose.DRAW;
        }
        return WinOrLose.LOSE;
    }

    private static WinOrLose judgeWinOrLose(int playerScore, int dealerScore) {
        if (playerScore > dealerScore) {
            return WinOrLose.WIN;
        }
        if (playerScore < dealerScore) {
            return WinOrLose.LOSE;
        }
        return WinOrLose.DRAW;
    }
}
