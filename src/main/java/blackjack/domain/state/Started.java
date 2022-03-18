package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import java.util.List;

public final class Started extends Running {

    private Started(Cards cards) {
        super(cards);
    }

    public static State start(List<Card> initialCards) {
        Cards cards = new Cards(initialCards);
        if (cards.isBlackjack()) {
            return new Blackjack(cards);
        }
        return new Started(cards);
    }
}
