package blackjack.domain.card_hand;

import java.util.List;

import blackjack.domain.Card;
import blackjack.domain.deck.CardDrawer;
import blackjack.domain.deck.CardHandInitializer;
import blackjack.domain.deck.Deck;

public final class DealerBlackjackCardHand implements BlackjackWinDeterminer {
    
    private static final int DEALER_DRAW_THRESHOLD = 16;
    
    private final BlackjackCardHand cardHand;
    
    public DealerBlackjackCardHand(final CardHandInitializer initializer) {
        this.cardHand = new BlackjackCardHand(initializer);
    }
    
    public List<Card> getInitialCards() {
        return List.of(cardHand.getCards().getFirst());
    }
    
    public void startAdding(final CardDrawer cardDrawer) {
        while (cardHand.getBlackjackSum() <= DEALER_DRAW_THRESHOLD) {
            cardHand.addCard(cardDrawer.draw());
        }
    }
    
    public List<Card> getCards() {
        return cardHand.getCards();
    }
    
    @Override
    public int getBlackjackSum() {
        return cardHand.getBlackjackSum();
    }
    
    @Override
    public int getSize() {
        return cardHand.getCards().size();
    }
}
