package blackjack.model.gamer;

import blackjack.model.GameRule;

public class Dealer extends Gamer {

    @Override
    public boolean canHit() {
        int cardScore = handDeck.calculateTotalScore();
        return GameRule.dealerHitRule(cardScore);
    }
}
