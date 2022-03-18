package blackjack.domain.state;

import blackjack.domain.Score;
import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import java.util.Objects;

public abstract class Finished implements State {

    private final Cards cards;

    protected Finished(Cards cards) {
        Objects.requireNonNull(cards, "[ERROR] 카드패는 null일 수 없습니다.");
        this.cards = cards;
    }

    @Override
    public State hit(Card card) {
        throw new UnsupportedOperationException("[ERROR] 게임을 진행할 수 없습니다.");
    }

    @Override
    public State stand() {
        throw new UnsupportedOperationException("[ERROR] 게임을 진행할 수 없습니다.");
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public Cards getCards() {
        return cards;
    }

    @Override
    public Score calculateScore() {
        return cards.calculateScore();
    }
}
