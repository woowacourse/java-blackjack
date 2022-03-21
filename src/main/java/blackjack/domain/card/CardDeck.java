package blackjack.domain.card;

import java.util.Collections;
import java.util.Stack;

public class CardDeck {
    private final Stack<Card> deck;

    public CardDeck() {
        this.deck = new Stack<>();
        for (CardType type : CardType.values()) {
            generateCardWith(type);
        }
        Collections.shuffle(deck);
    }

    private void generateCardWith(CardType type) {
        for (CardValue value : CardValue.values()) {
            deck.push(Card.of(type, value));
        }
    }

    public Card pickCard() {
        return deck.pop();
    }
}
