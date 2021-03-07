package blackjack.domain.gamer;

import blackjack.domain.Score;

public class Player extends Person {

    public Player(String name) {
        super(name);
    }

    @Override
    public boolean canDraw() {
        return Score.calculatorScore(cards) < Score.MAX_SCORE;
    }
}
