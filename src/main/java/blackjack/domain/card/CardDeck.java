package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CardDeck {

    private static final List<Card> cards;

    private List<Card> deck;

    static {
        cards = Stream.of(Denomination.values())
                .flatMap(denomination ->
                        Stream.of(Suit.values())
                                .map(suit -> new Card(denomination, suit)))
                .collect(Collectors.toList());
    }

    public CardDeck() {
        deck = new ArrayList<>(cards);
        Collections.shuffle(deck);
    }

    public Card getCard() {
        checkDeckEmpty();
        Card card = deck.get(0);
        deck.remove(0);
        return card;
    }

    private void checkDeckEmpty() {
        if (deck.isEmpty()) {
            deck = new ArrayList<>(cards);
        }
    }
}
