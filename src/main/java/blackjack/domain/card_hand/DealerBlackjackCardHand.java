package blackjack.domain.card_hand;

import java.util.List;

import blackjack.domain.Card;
import blackjack.domain.CardHandInitializer;
import blackjack.domain.Deck;

public class DealerBlackjackCardHand implements BlackjackCardHand {
    
    private static final int DEALER_DRAW_THRESHOLD = 16;
    
    private final CardHand cardHand;
    
    public DealerBlackjackCardHand(final CardHandInitializer initializer) {
        this.cardHand = new CardHand(initializer);
    }
    
    public List<Card> getInitialCards() {
        return List.of(cardHand.getCards().getFirst());
    }
    
    public void startAdding(final Deck deck) {
        while (cardHand.getSum() <= DEALER_DRAW_THRESHOLD) {
            cardHand.addCard(deck.draw());
        }
    }
    
    public List<Card> getCards() {
        return cardHand.getCards();
    }
    
    @Override
    public int getSum() {
        return cardHand.getSum();
    }
    
    @Override
    public int getSize() {
        return cardHand.getCards().size();
    }
}
