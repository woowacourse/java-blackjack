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
        throw new UnsupportedOperationException("해당 플레이어의 이미 턴이 종료되었습니다.");
    }

    @Override
    public int calculateScore() {
        return cardHand.sumAllScore();
    }
}
