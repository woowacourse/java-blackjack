package domain.gamer;

import domain.BlackJackScoreManager;
import domain.card.possessable.HandCards;

public class Player extends Gamer {
    public Player(String name) {
        super(name, new HandCards());
    }

    @Override
    public boolean canDrawMore() {
        return calculateScore() <= BlackJackScoreManager.BLACK_JACK_SCORE;
    }
}
