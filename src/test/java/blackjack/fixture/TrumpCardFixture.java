package blackjack.fixture;

import blackjack.domain.card.Rank;
import blackjack.domain.card.Suit;
import blackjack.domain.card.TrumpCard;

public class TrumpCardFixture {

    public static TrumpCard aceSpadeTrumpCard() {
        return new TrumpCard(Rank.ACE, Suit.SPADES);
    }

    public static TrumpCard aceCloverTrumpCard() {
        return new TrumpCard(Rank.ACE, Suit.CLUBS);
    }

    public static TrumpCard aceDiamondTrumpCard() {
        return new TrumpCard(Rank.ACE, Suit.DIAMONDS);
    }

    public static TrumpCard fourSpadeTrumpCard() {
        return new TrumpCard(Rank.FOUR, Suit.SPADES);
    }

    public static TrumpCard fiveSpadeTrumpCard() {
        return new TrumpCard(Rank.FIVE, Suit.SPADES);
    }

    public static TrumpCard fiveSpadeKingCard() {
        return new TrumpCard(Rank.KING, Suit.SPADES);
    }

    public static TrumpCard threeSpadeKingCard() {
        return new TrumpCard(Rank.THREE, Suit.SPADES);
    }
}
