package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import java.util.List;
import java.util.Objects;

public final class Started implements State {

    private final Cards cards;

    private Started(Cards cards) {
        Objects.requireNonNull(cards, "[ERROR] 카드패는 null일 수 없습니다.");
        this.cards = cards;
    }

    public static State start(List<Card> initialCards) {
        Cards cards = new Cards(initialCards);
        if (cards.isBlackjack()) {
            return new Blackjack(cards);
        }
        return new Started(cards);
    }

    @Override
    public State hit(Card card) {
        cards.add(card);
        return new Hit(cards);
    }

    @Override
    public State stand() {
        return new Stand(cards);
    }
}
