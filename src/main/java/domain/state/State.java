package domain.state;

import domain.card.Card;
import domain.card.Hand;

public abstract class State {
    
    private final Hand hand;
    
    State(Hand hand) {
        this.hand = hand;
    }
    
    public abstract State draw(Card card);
    
    protected Hand getHand() {
        return hand;
    }
    
    protected boolean isNotEnoughInitCardsCount() {
        return hand.isNotEnoughInitCardsCount();
    }
    
    protected boolean isBlackJack() {
        return hand.isBlackJack();
    }
}
