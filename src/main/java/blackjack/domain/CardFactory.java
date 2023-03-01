package blackjack.domain;

import java.util.ArrayList;
import java.util.List;

public class CardFactory {

    public static List<Card> of() {
        List<Card> cards = new ArrayList<>();
        for (CardSymbol cardSymbol : CardSymbol.values()) {
            for (CardNumber cardNumber : CardNumber.values()) {
                cards.add(new Card(cardNumber, cardSymbol));
            }
        }
        return cards;

    }
}
