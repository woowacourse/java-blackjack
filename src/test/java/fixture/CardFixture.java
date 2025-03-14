package fixture;

import domain.Card;
import domain.CardNumberType;
import domain.CardType;
import domain.Deck;
import java.util.ArrayList;
import java.util.List;

public class CardFixture {

    public static List<Card> createFixedCards() {
        return new ArrayList<>(List.of(
                new Card(CardNumberType.TWO, CardType.CLOVER),
                new Card(CardNumberType.THREE, CardType.CLOVER),
                new Card(CardNumberType.FOUR, CardType.CLOVER),
                new Card(CardNumberType.FIVE, CardType.CLOVER),
                new Card(CardNumberType.SIX, CardType.CLOVER),
                new Card(CardNumberType.SEVEN, CardType.CLOVER)
        ));
    }

    public static List<Card> createEmptyCards() {
        return new ArrayList<>();
    }
}


