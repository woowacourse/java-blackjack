package domain;

import domain.participant.Dealer;
import domain.participant.Player;

public enum GameResult {
    WIN,
    LOSE,
    DRAW;

    public GameResult getReverse() {
        return switch (this) {
            case LOSE -> WIN;
            case WIN -> LOSE;
            case DRAW -> DRAW;
        };
    }

    public static GameResult getResult(Player player, Dealer dealer) {
        if (player.isBurst()) {
            return GameResult.LOSE;
        }
        if (dealer.isBurst() || player.calculateScore() > dealer.calculateScore()) {
            return GameResult.WIN;
        }
        if (player.calculateScore() < dealer.calculateScore()) {
            return GameResult.LOSE;
        }
        return GameResult.DRAW;
    }

}
