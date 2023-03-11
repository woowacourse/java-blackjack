package domain.player.state;

import domain.card.Card;
import domain.card.Hand;

public abstract class Finished extends State {
    Finished(Hand hand) {
        super(hand);
    }
    
    @Override
    public State draw(Card card) {
        throw new IllegalStateException("더이상 카드를 뽑을 수 없습니다.");
    }
    
    @Override
    public State drawStop() {
        throw new IllegalStateException("이미 드로우가 끝난 상태에선 사용할 수 없는 기능입니다.");
    }
}
