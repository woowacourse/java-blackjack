package domain.player;

import domain.ScoreCalculator;
import domain.card.HandCards;

public class User extends BlackJackPlayer {
    public User(String name) {
        super(name, new HandCards());
    }

    @Override
    public boolean canDrawMore() {
        return calculateScore() <= ScoreCalculator.BLACK_JACK_SCORE;
    }
}
