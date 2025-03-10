package blackjack.domain.card_hand;

import blackjack.domain.card.Card;
import blackjack.domain.deck.BlackjackCardHandInitializer;
import blackjack.domain.deck.CardDrawer;

import java.util.List;

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
    
    public Card getInitialCard() {
        return cardHand.getCards().getFirst();
    }
    
    public int startAddingAndGetAddedSize(final CardDrawer cardDrawer) {
        final int beforeCount = cardHand.getCardCount();
        while (cardHand.getBlackjackSum() <= DEALER_DRAW_THRESHOLD) {
            cardHand.addCard(cardDrawer.draw());
        }
        return cardHand.getCardCount() - beforeCount;
    }
    
    public List<Card> getCards() {
        return cardHand.getCards();
    }
    
    @Override
    public int getBlackjackSum() {
        return cardHand.getBlackjackSum();
    }
    
    @Override
    public boolean isBust() {
        return cardHand.isBust();
    }
    
    @Override
    public boolean isBlackjack() {
        return cardHand.isBlackjack();
    }
}
