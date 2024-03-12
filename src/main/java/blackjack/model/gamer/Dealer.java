package blackjack.model.gamer;

import blackjack.model.GameRule;
import blackjack.model.card.Card;

public class Dealer extends Gamer {

    public Dealer() {
    }

    public Card getFirstCard() {
        return getHandDeck().get(0);
    }

    @Override
    public boolean canHit() {
        return GameRule.dealerHitRule(calculateTotalScore());
    }
}
