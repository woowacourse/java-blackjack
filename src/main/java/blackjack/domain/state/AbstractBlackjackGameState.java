package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import java.util.List;
import java.util.Objects;

public abstract class AbstractBlackjackGameState implements BlackjackGameState {

    final Cards cards;

    public AbstractBlackjackGameState(final Cards cards) {
        Objects.requireNonNull(cards, "cards는 null이 들어올 수 없습니다.");
        this.cards = cards;
    }

    @Override
    public final boolean isBust() {
        return cards.isBust();
    }

    @Override
    public final boolean isBlackjack() {
        return cards.isBlackjack();
    }

    @Override
    public final List<Card> cards() {
        return cards.cards();
    }
}
