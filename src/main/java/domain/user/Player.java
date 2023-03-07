package domain.user;

import domain.Card.Card;
import domain.Card.CardCollection;

public class Player implements Participant2 {
    
    CardCollection hand = new CardCollection();
    
    @Override
    public void addCard(final Card card) {
        this.hand = this.hand.add(card);
    }
    
    @Override
    public CardCollection getReadyCards() {
        if (this.hand.size() != 2) {
            throw new IllegalStateException("처음에는 2장의 카드만 가질 수 있습니다.");
        }
        return this.hand;
    }
    
    @Override
    public CardCollection getCards() {
        return this.hand;
    }
    
    @Override
    public boolean isAbleToDraw() {
        return this.hand.calculateScore() < 21;
    }
}
