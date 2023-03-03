package blackjack.domain.card;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DeckMaker {
    public List<Card> makeDeck() {
        return Stream.of(Shape.values())
                .flatMap(shape -> Stream.of(Letter.values())
                        .map(letter -> new Card(shape, letter)))
                .collect(Collectors.toList());
    }
}
