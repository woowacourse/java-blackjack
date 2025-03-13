package domain.result;

import domain.participant.Dealer;
import domain.participant.Player;

public enum GameResultStatus {
    WIN,
    DRAW,
    LOSE;

    public static GameResultStatus calculate(Player player, Dealer dealer) {
        if (player.isBust()) {
            return GameResultStatus.LOSE;
        }
        if (dealer.isBust()) {
            return GameResultStatus.WIN;
        }
        return compareScores(player, dealer);
    }

    private static GameResultStatus compareScores(Player player, Dealer dealer) {
        if (player.getScore() > dealer.getScore()) {
            return GameResultStatus.WIN;
        }
        if (player.getScore() == dealer.getScore()) {
            return GameResultStatus.DRAW;
        }
        return GameResultStatus.LOSE;
    }

    public boolean isEqualTo(GameResultStatus gameResultStatus) {
        return gameResultStatus == this;
    }
}
