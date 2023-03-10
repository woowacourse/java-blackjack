package domain.state;

import domain.card.Card;
import domain.card.Hand;

public class Bust extends State {
    Bust(Hand hand) {
        super(hand);
    }
    
    @Override
    public State draw(Card card) {
        throw new IllegalStateException("더이상 카드를 뽑을 수 없는 상태입니다.");
    }
}
