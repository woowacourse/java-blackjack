package first.domain.gamer;

import first.domain.card.possessable.HandCards;

import static first.domain.score.ScoreManagable.BLACK_JACK_SCORE;

public class Player extends Gamer {
    public Player(String name) {
        super(name, new HandCards());
    }

    @Override
    public boolean canDrawMore() {
        return BLACK_JACK_SCORE.isLargerThan(calculateScore());
    }
}
