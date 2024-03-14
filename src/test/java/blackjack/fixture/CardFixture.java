package blackjack.fixture;

import blackjack.domain.card.Card;
import blackjack.domain.card.Rank;
import blackjack.domain.card.Suit;

public class CardFixture {

    public static Card aceSpadeCard() {
        return new Card(Rank.ACE, Suit.SPADE);
    }

    public static Card aceCloverCard() {
        return new Card(Rank.ACE, Suit.CLOVER);
    }

    public static Card aceDiamondCard() {
        return new Card(Rank.ACE, Suit.DIAMOND);
    }

    public static Card fourSpadeCard() {
        return new Card(Rank.FOUR, Suit.SPADE);
    }

    public static Card fiveSpadeCard() {
        return new Card(Rank.FIVE, Suit.SPADE);
    }

    public static Card kingSpadeCard() {
        return new Card(Rank.KING, Suit.SPADE);
    }

    public static Card threeSpadeCard() {
        return new Card(Rank.THREE, Suit.SPADE);
    }
}
