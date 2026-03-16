package domain.card;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Stream;

public class CardDeck {

    private final Queue<Card> deck;

    private CardDeck(List<Card> cardList) {
        this.deck = new LinkedList<>(cardList);
    }

    public static CardDeck from(CardDeckInitializer cardDeckInitializer) {
        return new CardDeck(cardDeckInitializer.initialize());
    }

    public List<Card> draw(int count) {
        return Stream.generate(this::drawCard)
                .limit(count)
                .toList();
    }

    private Card drawCard() {
        if (deck.isEmpty()) {
            throw new IllegalArgumentException();
        }

        return deck.poll();
    }
}
