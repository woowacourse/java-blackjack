package blackjack.domain;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CardFactory {

    public List<Card> generate() {
        return Arrays.stream(Suit.values())
                .flatMap(this::getCardStream)
                .collect(Collectors.toList());
    }

    private Stream<Card> getCardStream(Suit suit) {
        return Arrays.stream(Denomination.values())
                .map(denomination -> new Card(suit, denomination));
    }
}
