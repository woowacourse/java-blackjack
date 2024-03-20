package blackjack.domain.card;

public class CardFixture {

    public static Card fromSuitCloverWith(Denomination denomination) {
        return new Card(Suit.CLOVER, denomination);
    }
}
