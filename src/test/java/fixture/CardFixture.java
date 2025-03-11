package fixture;

import domain.Card;
import domain.CardNumberType;
import domain.CardType;
import domain.GivenCards;
import java.util.ArrayList;
import java.util.List;

public class CardFixture {

    public static GivenCards createFilledGivenCards() {
        List<Card> cards = new ArrayList<>();
        for (int i = 0; i < 52; i++) {
            cards.add(new Card(CardNumberType.ACE, CardType.DIAMOND));
        }
        return GivenCards.create(cards);
    }
}
