package blackjack.domain.card;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class NotShuffledDeckFactory implements DeckFactory {

    @Override
    public Deck generate() {
        final List<Card> cards = generateCards();
        return new Deck(new ArrayDeque<>(cards));
    }

    private List<Card> generateCards() {
        return Arrays.stream(Shape.values())
                .flatMap(shape -> Arrays.stream(Symbol.values())
                        .map(symbol -> new Card(shape, symbol)))
                .collect(Collectors.toList());
    }
}
