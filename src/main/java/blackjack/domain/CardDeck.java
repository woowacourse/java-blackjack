package blackjack.domain;

import java.util.List;
import java.util.stream.IntStream;

public class CardDeck {

    private final List<Card> cards;

    public CardDeck(List<Card> cards) {
        this.cards = cards;
    }

    public Card popCard() {
        return cards.remove(0);
    }

    public List<Card> popCards(int count) {
        return IntStream.range(0, count)
                .mapToObj(i -> popCard())
                .toList();
    }
}
