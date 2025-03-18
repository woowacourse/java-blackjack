package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CardDeckGenerator {

    private static final List<Card> DEFAULT_CARDS;

    static {
        List<Card> newCards = new ArrayList<>();
        for (CardShape shape : CardShape.values()) {
            Arrays.stream(CardValue.values())
                    .map(cardValue -> new Card(shape, cardValue))
                    .forEach(newCards::add);
        }
        DEFAULT_CARDS = newCards.stream().toList();
    }

    public CardDeck makeShuffled() {
        List<Card> cards = new ArrayList<>(DEFAULT_CARDS);
        Collections.shuffle(cards);
        return new CardDeck(cards);
    }
}
