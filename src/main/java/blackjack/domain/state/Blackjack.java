package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardHand;

public class Blackjack implements State {
    private final CardHand cardHand;

    public Blackjack(final CardHand cardHand) {
        this.cardHand = cardHand;
    }

    @Override
    public State draw(final Card card) {
        throw new UnsupportedOperationException("블랙잭 상태에서 카드를 더 뽑을 수 없습니다.");
    }

    @Override
    public int calculateScore() {
        return cardHand.sumAllScore();
    }
}
