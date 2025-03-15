package blackjack.domain.card_hand;

import blackjack.domain.card.Card;
import blackjack.domain.deck.BlackjackCardHandInitializer;
import blackjack.domain.deck.CardDrawer;
import blackjack.util.GlobalValidator;

import java.util.List;

public final class DealerBlackjackCardHand {
    
    private static final int DEALER_DRAW_THRESHOLD = 16;
    
    private final BlackjackCardHand cardHand;
    
    private DealerBlackjackCardHand(final BlackjackCardHand cardHand) {
        GlobalValidator.validateNotNull(cardHand);
        this.cardHand = cardHand;
    }
    
    public static DealerBlackjackCardHand createWithInitialCards(final BlackjackCardHandInitializer initializer) {
        GlobalValidator.validateNotNull(initializer);
        return new DealerBlackjackCardHand(BlackjackCardHand.createWithInitialCards(initializer));
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
    
    public int getBlackjackSum() {
        return cardHand.getBlackjackSum();
    }
    
    public boolean isBust() {
        return cardHand.isBust();
    }
    
    public boolean isBlackjack() {
        return cardHand.isBlackjack();
    }
}
