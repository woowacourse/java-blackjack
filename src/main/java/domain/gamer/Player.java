package domain.gamer;

import domain.card.possessable.HandCards;
import domain.score.BlackJackScoreManager;

public class Player extends Gamer {
    public Player(String name) {
        super(name, new HandCards());
    }

    @Override
    public boolean canDrawMore() {
        return BlackJackScoreManager.BLACK_JACK_SCORE.isLargerThan(calculateScore());
    }
}
