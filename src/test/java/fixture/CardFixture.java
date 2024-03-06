package fixture;

import domain.Card;
import domain.Denomination;
import domain.Emblem;

public class CardFixture {
    public static Card 카드(Denomination denomination) {
        return new Card(denomination, Emblem.CLOVER);
    }

    public static Card 카드() {
        return 카드(Denomination.ACE);
    }
}
