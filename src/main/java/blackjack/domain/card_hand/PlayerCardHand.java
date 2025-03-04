package blackjack.domain.card_hand;

import blackjack.domain.Card;
import blackjack.domain.CardHandInitializer;

public class PlayerCardHand extends CardHand {
    
    public PlayerCardHand(final CardHandInitializer initializer) {
        super(initializer);
    }
    
    public void addCard(final Card card) {
        cards.add(card);
    }
}
