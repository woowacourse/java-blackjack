package blackjack.domain.state;

import blackjack.domain.card.Cards;

public abstract class Result extends TurnFinished {

    Result(Cards cards) {
        super(cards);
    }

    @Override
    public State judge(State result) {
        throw new IllegalStateException("이미 결과를 낸 상태입니다. 비교할 수 없습니다.");
    }
}
