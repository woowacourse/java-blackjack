package blackjack.domain;

import blackjack.domain.card.Card;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CardDeck {

    private final Queue<Card> cardDeck;

    public CardDeck(LinkedList<Card> cardDeck) {
        this.cardDeck = cardDeck;
    }

    public List<Card> drawInitialCard() {
        List<Card> cards = new ArrayList<>();
        cards.add(drawCard());
        cards.add(drawCard());
        return cards;
    }

    public Card drawCard() {
        return cardDeck.poll();
    }

    public int size() {
        return cardDeck.size();
    }
}
