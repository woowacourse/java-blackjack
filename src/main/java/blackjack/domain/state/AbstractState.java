package blackjack.domain.state;

import blackjack.domain.Score;
import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import java.util.List;
import java.util.Objects;

public abstract class AbstractState implements State {

    protected final Cards cards;

    protected AbstractState(Cards cards) {
        Objects.requireNonNull(cards, "[ERROR] 카드패는 null일 수 없습니다.");
        this.cards = cards;
    }

    @Override
    public List<Card> getCards() {
        return cards.getCards();
    }

    @Override
    public Score calculateScore() {
        return cards.calculateScore();
    }
}
