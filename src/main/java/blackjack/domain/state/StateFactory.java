package blackjack.domain.state;

import blackjack.domain.card.PlayingCard;

public class StateFactory {
    public static State draw(final PlayingCard first, final PlayingCard second) {
        final Cards cards = new Cards(first, second);
        if (cards.isBlackjack()) {
            return new Blackjack(cards);
        }
        return new Hit(cards);
    }
}
