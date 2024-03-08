package blackjack.fixture;

import blackjack.domain.card.Rank;
import blackjack.domain.card.Suit;
import blackjack.domain.card.TrumpCard;

public class TrumpCardFixture {

    public static TrumpCard aceSpadeTrumpCard() {
        return new TrumpCard(Rank.ACE, Suit.SPADE);
    }

    public static TrumpCard aceCloverTrumpCard() {
        return new TrumpCard(Rank.ACE, Suit.CLOVER);
    }

    public static TrumpCard aceDiamondTrumpCard() {
        return new TrumpCard(Rank.ACE, Suit.DIAMOND);
    }

    public static TrumpCard fourSpadeTrumpCard() {
        return new TrumpCard(Rank.FOUR, Suit.SPADE);
    }

    public static TrumpCard fiveSpadeTrumpCard() {
        return new TrumpCard(Rank.FIVE, Suit.SPADE);
    }

    public static TrumpCard fiveSpadeKingCard() {
        return new TrumpCard(Rank.KING, Suit.SPADE);
    }

    public static TrumpCard threeSpadeKingCard() {
        return new TrumpCard(Rank.THREE, Suit.SPADE);
    }
}
