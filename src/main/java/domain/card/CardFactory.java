package domain.card;

import java.util.ArrayList;
import java.util.List;

public class CardFactory {
    public static List<Card> create(Emblem emblem) {
        List<Card> cards = new ArrayList<>();

        for (Denomination denomination : Denomination.values()) {
            cards.add(new Card(denomination, emblem));
        }

        return cards;
    }
}
