package blackjack.domain.card;

import java.util.Stack;

public class DeckFactory {

    private static final Stack<Card> TRUMP;

    static {
        final Stack<Card> pack = new Stack<>();
        for (final Suit suit : Suit.values()) {
            for (final Number number : Number.values()) {
                pack.add(new Card(number, suit));
            }
        }
        TRUMP = pack;
    }

    public static Deck createWithCount(int count) {
        final Stack<Card> cards = new Stack<>();

        for (int i = 0; i < count; i++) {
            cards.addAll(TRUMP);
        }

        return new Deck(cards);
    }
}
