package blackjack.domain;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ShuffledDeckFactory implements DeckFactory {


    @Override
    public Deck generate() {
        final List<Card> cards = generateCards();
        Collections.shuffle(cards);

        return new Deck(new ArrayDeque<>(cards));
    }

    private List<Card> generateCards() {
        return Arrays.stream(Shape.values())
                .flatMap(shape -> Arrays.stream(Symbol.values())
                        .map(symbol -> new Card(shape, symbol)))
                .collect(Collectors.toList());
    }
}
