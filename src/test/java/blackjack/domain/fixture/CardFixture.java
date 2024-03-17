package blackjack.domain.fixture;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardSuit;

public class CardFixture {

    public static Card heartJack() {
        return new Card(CardSuit.HEART, CardNumber.JACK);
    }

    public static Card diamond3() {
        return new Card(CardSuit.DIAMOND, CardNumber.THREE);
    }

    public static Card cloverAce() {
        return new Card(CardSuit.CLOVER, CardNumber.ACE);
    }
}
