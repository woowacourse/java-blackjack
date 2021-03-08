package blackjack.domain.card;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Deck {
    private static final Stack<Card> cards;

    static {
        Stack<Card> deck = new Stack<>();
        Arrays.stream(Suit.values())
            .forEach(suit -> Arrays.stream(Denomination.values())
                .forEach(denomination -> deck.add(new Card(denomination, suit))));
        Collections.shuffle(deck);
        cards = deck;
    }

    public List<Card> pickInitialCards() {
        return Stream.<Card>builder()
            .add(cards.pop()).add(cards.pop())
            .build()
            .collect(Collectors.toList());
    }

    public Card pickSingleCard() {
        return cards.pop();
    }

    public List<Card> getCards() {
        return cards;
    }
}
