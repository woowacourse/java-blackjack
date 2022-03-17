package blackjack.domain.card;

import blackjack.domain.card.Card;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CardDeck {

    private final Queue<Card> cardDeck;

    public CardDeck(final List<Card> cardDeck) {
        this.cardDeck = (LinkedList<Card>) cardDeck;
    }

    public List<Card> selectOriginalCard() {
        List<Card> cards = new ArrayList<>();
        cards.add(selectCard());
        cards.add(selectCard());
        return cards;
    }

    public Card selectCard() {
        return cardDeck.poll();
    }

    public int size() {
        return cardDeck.size();
    }
}
