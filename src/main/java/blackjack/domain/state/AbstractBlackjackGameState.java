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
    public List<Card> cards() {
        return cards.cards();
    }
}
