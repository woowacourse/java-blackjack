package domain.state;

import domain.card.Card;
import domain.card.Hand;

public class BlackJack extends State {
    BlackJack(Hand hand) {
        super(hand);
    }
    
    @Override
    public State draw(Card card) {
        throw new IllegalStateException("더이상 카드를 뽑을 수 없습니다.");
    }
}
