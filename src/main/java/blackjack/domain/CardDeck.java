package blackjack.domain;

import blackjack.domain.strategy.CardGenerator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CardDeck {
    private final Queue<Card> cardDeck;

    public CardDeck(CardGenerator cardGenerator) {
        List<Card> cards = cardGenerator.generate();
        this.cardDeck = new LinkedList<>(cards);
    }

    public Card drawCard() {
        return cardDeck.poll();
    }

}
