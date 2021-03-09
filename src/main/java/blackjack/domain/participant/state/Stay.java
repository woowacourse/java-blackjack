package blackjack.domain.participant.state;

import blackjack.domain.carddeck.Card;
import java.util.ArrayList;
import java.util.List;

public class Stay implements State {

    private final List<Card> cards;

    public Stay(final List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    @Override
    public State draw(Card card) {
        throw new IllegalStateException("스테이에서는 더 이상 카드를 뽑을 수 없습니다.");
    }

    @Override
    public State stay() {
        throw new IllegalStateException();
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
