package domain.state;

import domain.Hand;
import domain.card.Card;

import java.util.List;

public abstract class Started implements ParticipantState {
    protected final Hand hand;

    protected Started(Hand hand) {
        this.hand = hand;
    }

    @Override
    public boolean isFinished() { return false; }

    @Override
    public boolean isBust() { return false; }

    @Override
    public boolean isBlackJack() { return false; }

    @Override
    public int getScore(){
        return hand.calculateScore();
    }

    @Override
    public List<Card> getCards(){
        return hand.getCards();
    }

    @Override
    public Card getFirstCard(){
        return hand.getFirstCard();
    }

    @Override
    public int getHandSize(){
        return hand.getSize();
    }
}
