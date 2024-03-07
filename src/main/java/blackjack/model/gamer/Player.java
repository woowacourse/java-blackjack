package blackjack.model.gamer;

import blackjack.model.GameRule;

public class Player extends Gamer {

    //TODO 원시값 포장
    private final String name;

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean canHit() {
        int cardScore = handDeck.calculateTotalScore();
        return GameRule.playerHitRule(cardScore);
    }
}
