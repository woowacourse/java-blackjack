package blackjack.model.gamer;

import blackjack.model.GameRule;

public class Player extends Gamer {

    private final Name name;

    private Player(String name) {
        this.name = new Name(name);
    }

    public static Player from(String playerName) {
        return new Player(playerName);
    }

    public String name() {
        return name.getName();
    }

    @Override
    public boolean canHit() {
        int totalScore = handDeck.score();
        return GameRule.playerHitRule(totalScore);
    }
}
