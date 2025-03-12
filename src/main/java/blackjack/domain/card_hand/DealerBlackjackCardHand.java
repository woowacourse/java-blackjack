package blackjack.domain.card_hand;

import java.util.List;

import blackjack.domain.card.Card;
import blackjack.domain.deck.CardDrawer;
import blackjack.domain.deck.BlackjackCardHandInitializer;

public final class DealerBlackjackCardHand implements BlackjackWinDeterminable {
    
    private static final int DEALER_DRAW_THRESHOLD = 16;
    
    private final BlackjackCardHand cardHand;
    
    public DealerBlackjackCardHand(final BlackjackCardHandInitializer initializer) {
        validateNotNull(initializer);
        this.cardHand = new BlackjackCardHand(initializer);
    }
    
    private void validateNotNull(final BlackjackCardHandInitializer initializer) {
        if (initializer == null) {
            throw new IllegalArgumentException("초기 카드 지급 방식은 null이 될 수 없습니다.");
        }
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
