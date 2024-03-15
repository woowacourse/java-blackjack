package blackjack.domain.card.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

public class Bust extends AbstractState {
    Bust(final Cards cards) {
        super(cards);
    }

    @Override
    public State draw(final Card card) {
        throw new IllegalStateException("버스트 상태일때는 카드를 받을 수 없습니다");
    }

    @Override
    public State stand() {
        throw new IllegalStateException("버스트 상태에서 스탠드를 할 수 없습니다.");
    }


    @Override
    public boolean isBust() {
        return true;
    }
}
