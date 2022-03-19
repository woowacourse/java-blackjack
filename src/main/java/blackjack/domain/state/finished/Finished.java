package blackjack.domain.state.finished;

import blackjack.domain.card.Card;
import blackjack.domain.participant.Cards;
import blackjack.domain.state.State;

public abstract class Finished implements State {

    private final Cards cards;

    Finished(Cards cards) {
        this.cards = cards;
    }

    @Override
    public final State draw(Card card) {
        throw new IllegalStateException("Finished 상태에선 카드를 더 받을 수 없습니다.");
    }

    @Override
    public final Cards getCards() {
        return cards;
    }

    @Override
    public final State stay() {
        throw new IllegalStateException("Finished 상태에선 상태를 변경할 수 없습니다.");
    }
}
