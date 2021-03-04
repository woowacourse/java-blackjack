package blackjack.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CardDeck {

    private static final int GAP_WITH_SIZE_AND_INDEX = 1;
    private static final int DEFAULT_CARD_DRAW_COUNTS = 2;

    private final List<Card> cardDeck;

    public CardDeck() {
        List<Card> cards = Arrays.stream(Symbol.values())
                                 .flatMap(this::generateCard)
                                 .collect(Collectors.toList());
        Collections.shuffle(cards);
        this.cardDeck = cards;
    }

    private Stream<Card> generateCard(Symbol symbol) {
        return Arrays.stream(Shape.values())
                     .map(shape -> new Card(symbol, shape));
    }

    int size() {
        return cardDeck.size();
    }

    public Card draw() {
        int lastIndex = cardDeck.size() - GAP_WITH_SIZE_AND_INDEX;
        return cardDeck.remove(lastIndex);
    }

    public Cards drawDefaultCards() {
        List<Card> cards = new ArrayList<>();
        for (int i = 0; i < DEFAULT_CARD_DRAW_COUNTS; i++) {
            cards.add(draw());
        }
        return new Cards(cards);
    }
}
