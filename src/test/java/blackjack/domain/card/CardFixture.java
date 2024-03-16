package blackjack.domain.card;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;

public class CardFixture {

    public static Card fromSuitCloverWith(Denomination denomination) {
        return new Card(Suit.CLOVER, denomination);
    }
}
