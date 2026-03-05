package domain.card;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CardDeck {

    private final Queue<Card> deck;

    private CardDeck(List<Card> cardList) {
        this.deck = new LinkedList<>(cardList);
    }

    public static CardDeck from(CardGenerator cardGenerator) {
        return new CardDeck(cardGenerator.generate());
    }

    public Card giveCard() {
        if (deck.isEmpty()) {
            throw new IllegalArgumentException();
        }

        return deck.poll();
    }

}
