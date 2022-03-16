package blackjack.domain.state;

import blackjack.domain.card.Cards;
import java.util.Objects;

public abstract class AbstractBlackjackGameState implements BlackjackGameState {

    private final Cards cards;

    AbstractBlackjackGameState(final Cards cards) {
        Objects.requireNonNull(cards, "cards는 null이 들어올 수 없습니다.");
        this.cards = cards;
    }

    boolean isBust() {
        return cards.isBust();
    }
}
