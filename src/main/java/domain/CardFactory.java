package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardFactory {

    public static List<Card> createShuffledCards() {
        List<Card> cards = new ArrayList<>();
        for (CardNumberType cardNumberType : CardNumberType.values()) {
            for (CardType cardType : CardType.values()) {
                cards.add(new Card(cardNumberType, cardType));
            }
        }
        Collections.shuffle(cards);
        return cards;
    }
}
