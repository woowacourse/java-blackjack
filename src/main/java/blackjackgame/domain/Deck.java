package blackjackgame.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private final List<Card> cards;
    private int cursorIndex;

    public Deck() {
        this.cursorIndex = 0;
        this.cards = generateCards();
        Collections.shuffle(cards);
    }

    public Deck(final List<Card> cards) {
        this.cursorIndex = 0;
        this.cards = cards;
    }

    private List<Card> generateCards() {
        List<Card> cards = new ArrayList<>();
        for (final Symbol symbol : Symbol.values()) {
            addCard(cards, symbol);
        }
        return cards;
    }

    private void addCard(final List<Card> cards, final Symbol symbol) {
        for (final CardValue cardValue : CardValue.values()) {
            cards.add(new Card(symbol, cardValue));
        }
    }

    public Card pickOne() {
        return cards.get(cursorIndex++);
    }
}
