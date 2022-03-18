package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import java.util.List;

public final class Ready {

    public static State start(Card card1, Card card2) {
        Cards cards = new Cards(List.of(card1, card2));
        if (cards.isBlackjack()) {
            return new Blackjack();
        }
        return new HitTurn(cards);
    }
}
