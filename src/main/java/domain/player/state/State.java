package domain.player.state;

import domain.card.Card;
import domain.card.Hand;
import domain.card.Score;

import java.util.List;

public abstract class State {
    
    private final Hand hand;
    
    State(Hand hand) {
        this.hand = hand;
    }
    
    public abstract State draw(Card card);
    public abstract double calculateProfit(double betAmount);
    public abstract State drawStop();
    
    public Score score() {
        return hand.getTotalScore();
    }
    
    public List<Card> getCards() {
        return hand.getCards();
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
