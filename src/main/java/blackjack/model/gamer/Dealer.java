package blackjack.model.gamer;

import blackjack.model.GameRule;

public class Dealer extends Gamer {

    @Override
    public boolean canHit() {
        int cardScore = deck.calculateTotalScore();
        return GameRule.dealerHitRule(cardScore);
    }
}
