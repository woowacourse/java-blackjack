package blackjack.domain;

import blackjack.domain.player.Player;

import static blackjack.domain.Status.BURST;

public enum WinOrLose {
    WIN("승"),
    LOSE("패");

    private final String message;

    WinOrLose(String message) {
        this.message = message;
    }

    public static WinOrLose calculate(Player dealer, Player gamer) {
        if (isOnlyDealerBurst(dealer, gamer) || hasPlayerHigherScoreThanDealer(dealer, gamer)) {
            return WIN;
        }

        return LOSE;
    }

    private static boolean isOnlyDealerBurst(Player dealer, Player gamer) {
        return dealer.getStatus() == BURST &&
            gamer.getStatus() != BURST;
    }

    private static boolean hasPlayerHigherScoreThanDealer(Player dealer, Player gamer) {
        return dealer.getStatus() != BURST &&
            gamer.getStatus() != BURST &&
            dealer.getScore() < gamer.getScore();
    }

    public WinOrLose reverse() {
        if (this == WIN) {
            return LOSE;
        }
        return WIN;
    }

    public String getMessage() {
        return message;
    }
}
