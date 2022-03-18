package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import java.util.List;

public final class Started {

    private final Cards cards;

    private Started(Cards cards) {
        this.cards = cards;
    }

    public static Object start(List<Card> initialCards) {
        Cards cards = new Cards(initialCards);
        if (cards.isBlackjack()) {
            return new Blackjack(cards);
        }
        return new Started(cards);
    }
}
