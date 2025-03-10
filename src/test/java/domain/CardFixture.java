package domain;

import java.util.ArrayList;
import java.util.List;

public class CardFixture {

    public static List<Card> createFilledCards() {
        List<Card> cards = new ArrayList<>();
        for (int i = 0; i < 52; i++) {
            cards.add(new Card(CardNumberType.ACE, CardType.DIAMOND));
        }
        return cards;
    }
}
