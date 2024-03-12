package blackjack.model.gamer;

import blackjack.model.GameRule;

public class Dealer extends Gamer {

    private Dealer() {
    }

    public static Dealer create() {
        return new Dealer();
    }

    @Override
    public boolean canHit() {
        int totalScore = handDeck.score();
        return GameRule.dealerHitRule(totalScore);
    }
}
