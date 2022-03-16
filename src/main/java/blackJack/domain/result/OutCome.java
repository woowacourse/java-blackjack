package blackJack.domain.result;

import blackJack.domain.participant.Dealer;
import blackJack.domain.participant.Player;

public enum OutCome {
    WIN("승"),
    DRAW("무"),
    LOSE("패")
    ;

    private final String result;

    OutCome(String result) {
        this.result = result;
    }

    public static OutCome calculatePlayerWinDrawLose(Player player, Dealer dealer) {
        if (player.isBust()) {
            return LOSE;
        }
        if (dealer.isBust()) {
            return WIN;
        }
        return getWinDrawLose(player, dealer);
    }

    private static OutCome getWinDrawLose(Player player, Dealer dealer) {
        final int playerScore = player.getScore();
        final int dealerScore = dealer.getScore();
        if (dealer.isBlackJack() && player.isBlackJack() ||
                !dealer.isBlackJack() && !player.isBlackJack() && playerScore == dealerScore) {
            return DRAW;
        }
        if (player.isBlackJack() || playerScore > dealerScore) {
            return WIN;
        }
        return LOSE;
    }

    public String getResult() {
        return result;
    }
}
