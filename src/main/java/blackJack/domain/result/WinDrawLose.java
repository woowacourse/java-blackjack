package blackJack.domain.result;

import blackJack.domain.participant.Dealer;
import blackJack.domain.participant.Participant;
import blackJack.domain.participant.Player;

public enum WinDrawLose {
    WIN("승"),
    DRAW("무"),
    LOSE("패")
    ;

    private static final int BLACK_JACK = 21;
    private final String result;

    WinDrawLose(String result) {
        this.result = result;
    }

    public static WinDrawLose calculatePlayerWinDrawLose(Player player, Dealer dealer) {
        if (overScore(player)) {
            return LOSE;
        }
        if (overScore(dealer)) {
            return WIN;
        }
        return getWinDrawLose(player, dealer);
    }

    private static WinDrawLose getWinDrawLose(Player player, Dealer dealer) {
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

    private static boolean overScore(Participant participant) {
        return participant.getScore() > BLACK_JACK;
    }

    public String getResult() {
        return result;
    }
}
