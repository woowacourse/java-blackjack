package blackjack.domain.user;

import blackjack.domain.card.Score;
import blackjack.domain.result.WinOrLose;

public class Player extends AbstractUser {
    private Player(String name) {
        super(name);
    }

    public static Player of(String name) {
        return new Player(name);
    }

    public WinOrLose isWinner(Dealer dealer) {
        Score dealerScore = dealer.calculateScore();
        boolean isWinner = calculateScore().isOver(dealerScore) && isNotBust();
        return WinOrLose.of(isWinner);
    }
}
