package blackjack.model.gamer;

import blackjack.model.gameRule.GameRule;

public class Player extends Gamer {

    private final Name name;

    private Player(Name name) {
        this.name = name;
    }

    public Player(String name) {
        this(new Name(name));
    }

    @Override
    public boolean canHit() {
        int score = calculateScore().getScore();
        return score <= GameRule.PLAYER_HIT_MAX_SCORE;
    }

    public String getName() {
        return name.getName();
    }
}
