package blackjack.domain.card;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CardDeck {

    private final Queue<Card> cardDeck;

    public CardDeck(final List<Card> cardDeck) {
        this.cardDeck = new LinkedList<>(cardDeck);
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
}
