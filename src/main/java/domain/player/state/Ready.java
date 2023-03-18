package domain.player.state;

import domain.card.Card;
import domain.card.Hand;

public class Ready extends State {
    public Ready(Card firstCard, Card secondCard) {
        super(new Hand());
        hand.addAllCard(firstCard, secondCard);
    }
    
    @Override
    public State drawStart() {
        if (isBlackJack()) {
            return new BlackJack(hand);
        }
        return new Hit(hand);
    }
    
    @Override
    public boolean isFinished() {
        return false;
    }
    
    @Override
    public State draw(Card card) {
        throw new UnsupportedOperationException("준비 상태에선 드로우할 수 없습니다.");
    }
    
    @Override
    public double calculateProfit(double betAmount) {
        throw new UnsupportedOperationException("준비 상태에선 수익률을 계산할 수 없습니다.");
    }
    
    @Override
    public State drawStop() {
        throw new UnsupportedOperationException("준비 상태에선 드로우 멈춤 기능을 사용할 수 없습니다.");
    }
}
