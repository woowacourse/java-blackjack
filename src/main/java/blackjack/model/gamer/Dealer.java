package blackjack.model.gamer;

import blackjack.model.GameRule;
import blackjack.model.card.Card;

public class Dealer extends Gamer {

    public Card getFirstCard() {
        return handDeck.getCards().get(0);
    }

    @Override
    public boolean canHit() {
        int cardScore = handDeck.calculateTotalScore();
        return GameRule.dealerHitRule(cardScore);
    }
}
