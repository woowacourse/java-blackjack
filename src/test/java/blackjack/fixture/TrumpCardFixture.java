package blackjack.fixture;

import blackjack.domain.card.Rank;
import blackjack.domain.card.Suit;
import blackjack.domain.card.Card;

public class TrumpCardFixture {

    public static Card aceSpadeTrumpCard() {
        return new Card(Rank.ACE, Suit.SPADES);
    }

    public static Card aceCloverTrumpCard() {
        return new Card(Rank.ACE, Suit.CLUBS);
    }

    public static Card aceDiamondTrumpCard() {
        return new Card(Rank.ACE, Suit.DIAMONDS);
    }

    public static Card fourSpadeTrumpCard() {
        return new Card(Rank.FOUR, Suit.SPADES);
    }

    public static Card fiveSpadeTrumpCard() {
        return new Card(Rank.FIVE, Suit.SPADES);
    }

    public static Card fiveSpadeKingCard() {
        return new Card(Rank.KING, Suit.SPADES);
    }

    public static Card threeSpadeKingCard() {
        return new Card(Rank.THREE, Suit.SPADES);
    }
}
