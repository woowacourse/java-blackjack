package blackjackgame.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cards {
    List<Card> cards;
    private int cursorIndex;

    public Cards() {
        this.cards = generateCards();
        Collections.shuffle(cards);
    }

    public Cards(List<Card> cards) {
        this.cards = cards;
        cursorIndex = 0;
    }

    private List<Card> generateCards() {
        List<Card> cards = new ArrayList<>();
        for (Symbol symbol : Symbol.values()) {
            addCard(cards, symbol);
        }
        return cards;
    }

    private void addCard(List<Card> cards, Symbol symbol) {
        for (CardValue cardValue : CardValue.values()) {
            cards.add(new Card(symbol, cardValue));
        }
    }

    public Card pickOne() {
        return cards.get(cursorIndex++);
    }
}
