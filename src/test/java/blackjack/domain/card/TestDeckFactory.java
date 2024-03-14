package blackjack.domain.card;

import java.util.List;
import java.util.Stack;
import java.util.stream.Stream;

public class TestDeckFactory implements DeckFactory {

    /**
     * 카드 순서 : Ace - HEART, SPADE, CLUB, DIAMOND
     * 2 - HEART, SPADE, CLUB, DIAMOND
     * ...
     * KING - HEART, SPADE, CLUB, DIAMOND
     */
    @Override
    public Stack<Card> generate() {
        final List<Card> cards = Stream.of(Number.values())
                .flatMap(number -> Stream.of(Suit.values())
                        .map(suit -> new Card(number, suit)))
                .toList();

        final Stack<Card> deck = new Stack<>();
        deck.addAll(cards);
        return deck;
    }
}
