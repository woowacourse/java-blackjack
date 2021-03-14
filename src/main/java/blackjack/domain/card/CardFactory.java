package blackjack.domain.card;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class CardFactory {

    private static final List<Card> normalCards = initNormalCards();

    public static List<Card> getNormalCards() {
        return normalCards;
    }

    private static List<Card> initNormalCards() {
        return Arrays.stream(Symbol.values())
                .flatMap(symbol ->
                        Arrays.stream(CardNumber.values())
                                .map(cardNumber -> new Card(symbol, cardNumber))
                ).collect(toList());
    }
}
