package blackjack.domain;

public class CardFixture {

    public static Card fromSuitCloverWith(Denomination denomination) {
        return new Card(Suit.CLOVER, denomination);
    }
}
