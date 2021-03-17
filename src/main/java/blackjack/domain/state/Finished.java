package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Score;

import java.util.List;

public abstract class Finished implements State {
    private Cards cards;

    public Finished(Cards cards) {
        this.cards = cards;
    }

    @Override
    public State draw(Card card) {
        throw new UnsupportedOperationException("카드를 뽑을 수 없습니다.");
    }

    @Override
    public List<Card> getCards() {
        return cards.getCards();
    }

    @Override
    public Score score() {
        return cards.scores();
    }

    @Override
    public State stay() {
        throw new UnsupportedOperationException("stay 할 수 없습니다.");
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public boolean isBlackjack() {
        return false;
    }

    @Override
    public boolean isBust() {
        return false;
    }
}
