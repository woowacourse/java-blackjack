package blackjack.util;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.Symbol;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CardFactory {

    private static final List<Card> normalCards = initNormalCards();

    public static List<Card> getNormalCards() {
        return normalCards;
    }

    private static List<Card> initNormalCards() {

        return Arrays.stream(Symbol.values())
            .flatMap(CardFactory::createCard)
            .collect(Collectors.toList());
    }

    private static Stream<Card> createCard(Symbol symbol) {
        return Arrays.stream(CardNumber.values())
            .map(cardNumber -> new Card(symbol, cardNumber));
    }

}
