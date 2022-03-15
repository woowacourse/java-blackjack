package blackjack.domain;

import static blackjack.domain.Denomination.*;

import java.util.List;

public class Player extends Gamer {

    public Player(String name, List<Card> cards) {
        super(name, cards);
    }

    @Override
    public boolean canHit() {
        return getTotalScore() <= BLACKJACK_SCORE;
    }

    @Override
    public GameResult createResult(Gamer dealer) {
        if (isBust()) {
            return GameResult.LOSE;
        }

        if (dealer.isBust()) {
            return GameResult.WIN;
        }

        return GameResult.of(getTotalScore(), dealer.getTotalScore());
    }
}
