package blackjack.domain.user;

import blackjack.domain.card.Score;

public class DefaultPlayer extends AbstractPlayer {
    private DefaultPlayer(String name) {
        super(name);
    }

    public static DefaultPlayer of(String name) {
        return new DefaultPlayer(name);
    }

    @Override
    public Boolean isWinner(Score dealerScore) {
        if (isBust()) {
            return false;
        }
        if (dealerScore.isOver(21)) {
            return true;
        }
        return calculateScore().isOver(dealerScore);
    }
}
