package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import java.util.Objects;

public class Hit implements State {

    private final Cards cards;

    Hit(Cards cards) {
        Objects.requireNonNull(cards, "[ERROR] 카드패는 null일 수 없습니다.");
        this.cards = cards;
    }

    @Override
    public State hit(Card card) {
        cards.add(card);
        if (cards.isBust()) {
            return new Bust(cards);
        }
        return new Hit(cards);
    }

    @Override
    public State stand() {
        return new Stand(cards);
    }
}
