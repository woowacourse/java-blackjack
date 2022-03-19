package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

public final class HitTurn extends Started {
    HitTurn(Cards cards) {
        super(cards);
    }

    @Override
    public State stand() {
        return new Stand(getCards());
    }

    @Override
    public State hit(Card card) {
        getCards().add(card);
        if (isBust()) {
            return new Bust(getCards());
        }
        return new HitTurn(getCards());
    }

    @Override
    public double prizeRate() {
        throw new IllegalStateException("아직 턴이 끝나지 않아서 수익을 계산할 수 없습니다.");
    }

    @Override
    public State judge(State result) {
        throw new IllegalStateException("아직 턴이 끝나지 않아서 승패를 판단할 수 없습니다.");
    }

    @Override
    public boolean isHitTurn() {
        return true;
    }
}
