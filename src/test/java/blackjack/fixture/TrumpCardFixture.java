package blackjack.fixture;

import blackjack.domain.card.Card;
import blackjack.domain.card.Rank;
import blackjack.domain.card.Suit;

public class TrumpCardFixture {

    public static Card aceSpadeTrumpCard() {
        return new Card(Rank.ACE, Suit.SPADE);
    }

    public static Card aceCloverTrumpCard() {
        return new Card(Rank.ACE, Suit.CLOVER);
    }

    public static Card aceDiamondTrumpCard() {
        return new Card(Rank.ACE, Suit.DIAMOND);
    }

    public static Card fourSpadeTrumpCard() {
        return new Card(Rank.FOUR, Suit.SPADE);
    }

    public static Card fiveSpadeTrumpCard() {
        return new Card(Rank.FIVE, Suit.SPADE);
    }

    public static Card kingSpadeTrumpCard() {
        return new Card(Rank.KING, Suit.SPADE);
    }

    public static Card threeSpadeTrumpCard() {
        return new Card(Rank.THREE, Suit.SPADE);
    }
}
