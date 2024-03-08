package blackjack.model.gamer;

import blackjack.model.GameRule;

public class Player extends Gamer {

    private final Name playerName;

    public Player(String playerName) {
        this.playerName = new Name(playerName);
    }

    public String getPlayerName() {
        return playerName.getName();
    }

    @Override
    public boolean canHit() {
        int cardScore = handDeck.calculateTotalScore();
        return GameRule.playerHitRule(cardScore);
    }
}
