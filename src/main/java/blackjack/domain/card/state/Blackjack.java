package blackjack.domain.card.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

public class Blackjack extends AbstractState {
    Blackjack(final Cards cards) {
        super(cards);
    }

    @Override
    public State draw(final Card card) {
        throw new IllegalStateException("블랙잭 상태일때는 카드를 받을 수 없습니다");
    }

    @Override
    public State stand() {
        throw new IllegalStateException("블랙잭 상태에서 스탠드를 할 수 없습니다.");
    }


    @Override
    public boolean isBlackjack() {
        return true;
    }
}
