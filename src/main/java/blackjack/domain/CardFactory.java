package blackjack.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CardFactory {

    public static List<Card> of() {
        List<Card> cards = new ArrayList<>();

        for (CardSymbol cardSymbol : CardSymbol.values()) {
            Arrays.stream(CardNumber.values())
                    .forEach(cardNumber -> cards.add(new Card(cardNumber, cardSymbol)));
        }
        return cards;
    }
}
