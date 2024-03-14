package blackjack.domain.card;

import java.util.Collections;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DeckShuffleFactory implements DeckFactory {

    @Override
    public Stack<Card> generate() {
        final List<Card> cards = Stream.of(Number.values())
                .               flatMap(number -> Stream.of(Suit.values())
                                                .map(suit -> new Card(number, suit)))
                                .collect(Collectors.toList());
        Collections.shuffle(cards);

        final Stack<Card> deck = new Stack<>();
        deck.addAll(cards);
        return deck;
    }
}
