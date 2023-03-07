package blackjackgame.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private static final int MAX_CARDS_NUMBER = 52;
    private final List<Card> cards;
    private int cursorIndex;

    public Deck(final List<Card> cards) {
        this.cursorIndex = 0;
        this.cards = cards;
    }

    public Deck() {
        this.cards = generateCards();
        shuffle();
    }

    private void shuffle() {
        this.cursorIndex = 0;
        Collections.shuffle(cards);
    }

    public Card pickOne() {
        if (cursorIndex == MAX_CARDS_NUMBER) {
            shuffle();
        }
        return cards.get(cursorIndex++);
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
}
