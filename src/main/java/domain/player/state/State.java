package domain.player.state;

import domain.card.Card;
import domain.card.Hand;
import domain.card.Score;

import java.util.List;
import java.util.Objects;

public abstract class State {
    
    private final Hand hand;
    
    State(Hand hand) {
        this.hand = hand;
    }
    
    public abstract State draw(Card card);
    public abstract double calculateProfit(double betAmount);
    public abstract State drawStop();
    public abstract boolean isFinished();
    public State drawStart() {
        throw new UnsupportedOperationException("드로우 시작 상태로 전이하는 기능은 Ready 상태일때만 사용 가능합니다.");
    }
    
    public Score score() {
        return hand.getTotalScore();
    }
    
    public List<Card> getCards() {
        return hand.getCards();
    }
    
    protected Hand getHand() {
        return hand;
    }
    
    protected boolean isBlackJack() {
        return hand.isBlackJack();
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        State state = (State) o;
        return Objects.equals(hand, state.hand);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(hand);
    }
    
    @Override
    public String toString() {
        return "State{" +
                "hand=" + hand +
                '}';
    }
}
