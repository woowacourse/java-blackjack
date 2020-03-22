package blackjack.domain.card;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class CardFactory {
    private final List<Card> cards;
    private static CardFactory instance = new CardFactory();

    private CardFactory() {
        this.cards = Collections.unmodifiableList(generateAllCards());
    }

    private List<Card> generateAllCards() {
        List<Card> cards = new LinkedList<>();
        Arrays.stream(Suit.values()).forEach(suit -> generateCardsBySuit(cards, suit));
        return cards;
    }

    private void generateCardsBySuit(List<Card> cards, Suit suit) {
        Arrays.stream(Symbol.values())
                .map(symbol -> new Card(suit, symbol))
                .forEach(cards::add);
    }

    public static CardFactory getInstance() {
        return instance;
    }

    public List<Card> issueNewDeck() {
        return new LinkedList<>(cards);
    }
}
