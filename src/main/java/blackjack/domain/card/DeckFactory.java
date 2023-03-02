package blackjack.domain.card;

import java.util.Stack;

public class DeckFactory {

    public static Deck createWithCount(final Stack<Card> pack, int count) {
        final Stack<Card> cards = new Stack<>();

        while (count-- > 0) {
            cards.addAll(pack);
        }

        return new Deck(cards);
    }
}
