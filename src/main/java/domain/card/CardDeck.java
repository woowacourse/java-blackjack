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

    public CardBundle draw(CardBundle cardBundle, int count) {
        List<Card> additionalCards = Stream.generate(this::drawCard)
                .limit(count)
                .toList();

        return cardBundle.addUp(CardBundle.from(additionalCards));
    }

    private Card drawCard() {
        if (deck.isEmpty()) {
            throw new IllegalArgumentException();
        }

        return deck.poll();
    }
}
