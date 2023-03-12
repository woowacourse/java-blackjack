package blackjack.domain;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ShuffleCardsGenerator implements CardsGenerator {
    @Override
    public List<Card> generator() {
        List<Card> cards = Arrays.stream(CardNumber.values())
                .flatMap(cardNumber -> Arrays.stream(CardSymbol.values())
                        .map(cardSymbol -> new Card(cardNumber, cardSymbol)))
                .collect(Collectors.toList());
        Collections.shuffle(cards);
        return cards;
    }
}
