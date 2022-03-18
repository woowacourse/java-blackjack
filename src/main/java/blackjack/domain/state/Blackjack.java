package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import java.util.Objects;

public final class Blackjack implements State {

    private final Cards cards;

    Blackjack(Cards cards) {
        Objects.requireNonNull(cards, "[ERROR] 카드패는 null일 수 없습니다.");
        this.cards = cards;
    }

    @Override
    public State hit(Card card) {
        throw new UnsupportedOperationException("[ERROR] 게임을 진행할 수 없습니다.");
    }
}
