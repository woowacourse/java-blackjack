package fixture;

import domain.card.Card;
import domain.card.Denomination;
import domain.card.Suit;
import domain.card.deck.CardDeck;
import java.util.Arrays;
import java.util.List;

public class CardFixture {
    public static Card 카드(Denomination denomination) {
        return new Card(denomination, Suit.CLOVER);
    }

    public static Card 카드() {
        return 카드(Denomination.ACE);
    }

    public static List<Card> 카드들(Card... cards) {
        return Arrays.asList(cards);
    }

    public static CardDeck 카드덱(Denomination... denominations) {
        return new CardDeck(Arrays.stream(denominations)
                .map(CardFixture::카드)
                .toList());
    }
}
