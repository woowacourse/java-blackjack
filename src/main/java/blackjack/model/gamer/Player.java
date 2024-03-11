package blackjack.model.gamer;

import blackjack.model.GameRule;

public class Player extends Gamer {

    private final Name playerName;

    public Player(String playerName) {
        this.playerName = new Name(playerName);
    }

    public String name() {
        return playerName.getName();
    }

    @Override
    public boolean canHit() {
        int totalScore = handDeck.score();
        return GameRule.playerHitRule(totalScore);
    }
}
