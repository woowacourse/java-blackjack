package fixture;

import domain.Card;
import java.util.ArrayList;
import java.util.List;

public class CardFixture {
    public static List<Card> deckFixture = new ArrayList<>();

    static {
        for (int i = 0; i < 52; i++) {
            deckFixture.add(Card.random(new TestNumberGenerator()));
        }
    }

    public static Card of(int index) {
        return deckFixture.get(index);
    }
}
