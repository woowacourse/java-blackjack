package blackjack.domain.card_hand;

import blackjack.domain.card.Card;
import blackjack.domain.deck.BlackjackCardHandInitializer;
import blackjack.domain.deck.CardDrawer;

import java.util.List;

public final class DealerBlackjackCardHand extends AbstractBlackjackCardHand {
    
    private static final int DEALER_DRAW_THRESHOLD = 16;
    
    public DealerBlackjackCardHand(final BlackjackCardHandInitializer initializer) {
        super(initializer);
    }
    
    @Override
    public List<Card> getInitialCards() {
        return List.of(getCards().getFirst());
    }
    
    public int startAddingAndGetAddedSize(final CardDrawer cardDrawer) {
        final int beforeCount = getCardCount();
        while (getBlackjackSum() <= DEALER_DRAW_THRESHOLD) {
            addCard(cardDrawer.draw());
        }
        return getCardCount() - beforeCount;
    }
    
    public double calculateTotalIncome(final List<PlayerBettingBlackjackCardHand> cardHands) {
        double totalIncomeOfPlayer = 0;
        for (PlayerBettingBlackjackCardHand cardHand : cardHands) {
            totalIncomeOfPlayer += cardHand.calculateIncome(this);
        }
        return -totalIncomeOfPlayer;
    }
}
