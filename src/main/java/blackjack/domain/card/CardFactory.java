package blackjack.domain.card;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class CardFactory {
    private final List<Card> cards;

    private CardFactory() {
        List<Card> cards = new LinkedList<>();
        for (Suit suit : Suit.values()) {
            for (Symbol symbol : Symbol.values()) {
                cards.add(new Card(suit, symbol));
            }
        }
        this.cards = Collections.unmodifiableList(cards);
    }

    public static CardFactory getInstance() {
        return CardFactorySingletonHolder.instance;
    }

    public LinkedList<Card> issueNewCards() {
        return new LinkedList<>(cards);
    }

    private static class CardFactorySingletonHolder {
        private static final CardFactory instance = new CardFactory();
    }
}
