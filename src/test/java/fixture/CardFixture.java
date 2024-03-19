package fixture;

import domain.card.Card;
import domain.card.Denomination;
import domain.card.Suit;

public class CardFixture {
    public static Card 카드(Denomination denomination) {
        return new Card(denomination, Suit.CLOVER);
    }

    public static Card 카드() {
        return 카드(Denomination.ACE);
    }
}
