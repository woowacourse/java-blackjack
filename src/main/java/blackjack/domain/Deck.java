package blackjack.domain;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class Deck {
    private final Stack<Card> cards;

    public Deck() {
        cards = init();
    }

    private Stack<Card> init() {
        Stack<Card> cards = new Stack<>();
        Arrays.stream(Suit.values())
            .forEach(suit -> Arrays.stream(Denomination.values())
                .forEach(denomination -> cards.add(new Card(denomination, suit))));
        return cards;
    }

    public List<Card> getCards() {
        return cards;
    }
}
