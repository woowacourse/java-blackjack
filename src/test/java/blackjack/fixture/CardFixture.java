package blackjack.fixture;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;

public class CardFixture {

    public static Card 카드(Denomination denomination) {
        return new Card(Suit.HEART, denomination);
    }
}
