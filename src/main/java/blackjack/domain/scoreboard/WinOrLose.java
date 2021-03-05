package blackjack.domain.scoreboard;

import blackjack.domain.user.Dealer;
import blackjack.domain.user.Status;
import blackjack.domain.user.User;

public enum WinOrLose {
    WIN("승"),
    DRAW("무"),
    LOSE("패");

    private final String character;

    WinOrLose(String character) {
        this.character = character;
    }

    public static WinOrLose decideWinOrLose(User user, Dealer dealer) {
        if (dealer.isSameStatus(Status.BURST)) {
            return decideWinOrLoseWhenDealerIsBurst(user);
        }

        return decideWinOrLoseWhenDealerIsNotBurst(user, dealer);
    }

    private static WinOrLose decideWinOrLoseWhenDealerIsBurst(User user) {
        if (user.isSameStatus(Status.BURST)) {
            return DRAW;
        }
        return WIN;
    }

    private static WinOrLose decideWinOrLoseWhenDealerIsNotBurst(User user, Dealer dealer) {
        if (user.isSameStatus(Status.BURST)) {
            return LOSE;
        }

        if (!user.isSameStatus(Status.BURST) && user.calculateScore() > dealer.calculateScore()) {
            return WIN;
        }

        return DRAW;
    }

    public static WinOrLose opposite(WinOrLose winOrLose) {
        if (winOrLose == WIN) {
            return LOSE;
        }
        if (winOrLose == LOSE) {
            return WIN;
        }
        return winOrLose;
    }

    public String getCharacter() {
        return character;
    }
}
