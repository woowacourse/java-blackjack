package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

public abstract class TurnFinished extends Started {

    TurnFinished(Cards cards) {
        super(cards);
    }

    @Override
    public State stand() {
        throw new IllegalStateException("턴이 종료되어서 스탠드할 수 없습니다.");
    }

    @Override
    public State hit(Card card) {
        throw new IllegalStateException("턴이 종료되어서 히트할 수 없습니다.");
    }

    @Override
    public double prizeRate() {
        throw new IllegalStateException("아직 결과를 내지 않아서 수익을 계산할 수 없습니다.");
    }

    @Override
    public boolean isHitTurn() {
        return false;
    }
}
