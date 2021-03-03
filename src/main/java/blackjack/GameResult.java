package blackjack;

import blackjack.domain.Status;
import blackjack.domain.player.Player;

public enum GameResult {
    WIN("승"),
    LOSE("패");

    private final String message;

    GameResult(String message) {
        this.message = message;
    }

    public static GameResult calculate(Player dealer, Player gamer) {
        if (isOnlyDealerBursted(dealer, gamer) || hasPlayerHigherScoreThanDealer(dealer, gamer)) {
            return GameResult.WIN;
        }

        return GameResult.LOSE;
    }

    private static boolean isOnlyDealerBursted(Player dealer, Player gamer) {
        return dealer.getStatus() == Status.BURST &&
            gamer.getStatus() != Status.BURST;
    }

    private static boolean hasPlayerHigherScoreThanDealer(Player dealer, Player gamer) {
        return dealer.getStatus() != Status.BURST &&
            gamer.getStatus() != Status.BURST &&
            dealer.getScore() < gamer.getScore();
    }

    public GameResult reverse() {
        if (this == WIN) {
            return LOSE;
        }
        return WIN;
    }

    public String getMessage() {
        return message;
    }
}
