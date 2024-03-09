package blackjack.domain;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CardFactory {

    public List<Card> createBlackJackCard() {
        return Arrays.stream(CardSuit.values())
                .flatMap(suit -> Arrays.stream(CardNumber.values())
                        .map(number -> new Card(suit, number)))
                .collect(Collectors.toList());
    }

}
