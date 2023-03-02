package blackjack.domain.card;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CardFactory {
    public static List<Card> of() {
        return Arrays.stream(CardSymbol.values())
                .flatMap(cardSymbol -> Arrays.stream(CardNumber.values())
                        .map(cardNumber -> new Card(cardNumber, cardSymbol)))
                .collect(Collectors.toList());
    }
}
