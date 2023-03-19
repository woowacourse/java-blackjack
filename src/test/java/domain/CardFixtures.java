package domain;

import domain.card.Card;
import domain.card.CardNumber;
import domain.card.CardType;

public class CardFixtures {

    public static Card of(CardType cardType, CardNumber cardNumber) {
        return new Card(cardType, cardNumber);
    }

    public static Card ofNumber(CardNumber cardNumber) {
        return new Card(CardType.SPADE, cardNumber);
    }
}
