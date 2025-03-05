package blackjack.domain.card_hand;

import java.util.List;

import blackjack.domain.Card;
import blackjack.domain.CardHandInitializer;
import blackjack.domain.Deck;

public class DealerCardHand extends CardHand {
    
    public static final int DEALER_DRAW_THRESHOLD = 16;
    
    public DealerCardHand(final CardHandInitializer initializer) {
        super(initializer);
    }
    
    @Override
    public List<Card> getInitialCards() {
        return List.of(cards.getFirst());
    }
    
    public void startAdding(final Deck deck) {
        while (getSum() <= DEALER_DRAW_THRESHOLD) {
            cards.add(deck.draw());
        }
    }
}
