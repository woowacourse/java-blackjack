package domain.state;

import domain.card.Card;
import domain.card.Hand;

public class Hit extends State {
    public Hit(Hand hand) {
        super(hand);
    }
    
    @Override
    public State draw(Card card) {
        getHand().addCard(card);
        if (getHand().isBust()) {
            return new Bust(getHand());
        }
        
        return this;
    }
    
    @Override
    public double calculateProfit(int betAmount) {
        throw new IllegalStateException("아직 배팅 금액을 계산할 수 없는 상태입니다.");
    }
}
