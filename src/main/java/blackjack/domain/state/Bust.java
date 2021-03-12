package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

public class Bust implements State{
    private static final String BUST_ADD_CARD_ERROR_MESSAGE = "블랙잭일 때에는 카드를 받을 수 없습니다.";
    private final Cards cards;

    public Bust(Cards cards) {
        this.cards = cards;
    }

    @Override
    public Cards cards() {
        return cards;
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public State addCard(Card card) {
        throw new UnsupportedOperationException(BUST_ADD_CARD_ERROR_MESSAGE);
    }

    @Override
    public State stay() {
        throw new UnsupportedOperationException();
    }

    @Override
    public double earningsRate() {
        return -1;
    }
}
