package blackjack.domain;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CardFactory {

    public List<Card> createBlackJackCard() {
        return Arrays.stream(CardSuit.values())
                .flatMap(this::createCardWith)
                .collect(Collectors.toList());
    }

    private Stream<Card> createCardWith(CardSuit suit) {
        return Arrays.stream(CardNumber.values())
                .map(number -> new Card(suit, number));
    }
}
