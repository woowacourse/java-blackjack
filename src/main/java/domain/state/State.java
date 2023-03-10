package domain.state;

import domain.card.Card;
import domain.card.Hand;
import domain.card.Score;

public abstract class State {
    
    private final Hand hand;
    
    State(Hand hand) {
        this.hand = hand;
    }
    
    public abstract State draw(Card card);
    public abstract double calculateProfit(int betAmount);
    
    protected Score score() {
        return hand.getTotalScore();
    }
    
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
