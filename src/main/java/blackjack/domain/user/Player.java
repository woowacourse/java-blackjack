package blackjack.domain.user;

import blackjack.domain.card.Score;

public class Player extends AbstractUser {
    private Player(String name) {
        super(name);
    }

    public static Player of(String name) {
        return new Player(name);
    }

    @Override
    public Boolean isWinner(Score dealerScore) {
        return calculateScore().isOver(dealerScore) && isNotBust();
    }
}
