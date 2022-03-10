package blackjack.domain.card;

import java.util.Collections;
import java.util.Stack;

public class CardDeck {
    private static final String NO_CARD_EXCEPTION_MESSAGE = "덱에 남은 카드가 없습니다";
    private final Stack<Card> cards;

    private CardDeck() {
        this.cards = new Stack<>();
        for (Denomination value : Denomination.values()) {
            addCards(value);
        }
        Collections.shuffle(cards);
    }

    public static CardDeck getInstance() {
        return new CardDeck();
    }

    public int size() {
        return cards.size();
    }

    public Card draw() {
        validateSize();
        return cards.pop();
    }

    private void addCards(Denomination value) {
        for (Suit suit : Suit.values()) {
            cards.push(Card.of(value, suit));
        }
    }

    private void validateSize() {
        if (cards.isEmpty()) {
            throw new ArrayIndexOutOfBoundsException(NO_CARD_EXCEPTION_MESSAGE);
        }
    }
}
