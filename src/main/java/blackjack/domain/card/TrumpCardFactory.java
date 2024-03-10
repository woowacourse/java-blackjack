package blackjack.domain.card;

import java.util.ArrayList;
import java.util.List;

public class TrumpCardFactory {

    private TrumpCardFactory() {
    }

    public static List<TrumpCard> createCards() {
        List<TrumpCard> trumpCards = new ArrayList<>();

        trumpCards.addAll(createSpadeCards());
        trumpCards.addAll(createDiamondCards());
        trumpCards.addAll(createHeartCards());
        trumpCards.addAll(createCloverCards());

        return trumpCards;
    }

    private static List<TrumpCard> createHeartCards() {
        List<TrumpCard> trumpCards = new ArrayList<>();

        for (Rank rank : Rank.values()) {
            trumpCards.add(new TrumpCard(rank, Suit.HEART));
        }

        return trumpCards;
    }

    private static List<TrumpCard> createSpadeCards() {
        List<TrumpCard> trumpCards = new ArrayList<>();

        for (Rank rank : Rank.values()) {
            trumpCards.add(new TrumpCard(rank, Suit.SPADE));
        }

        return trumpCards;
    }

    private static List<TrumpCard> createCloverCards() {
        List<TrumpCard> trumpCards = new ArrayList<>();

        for (Rank rank : Rank.values()) {
            trumpCards.add(new TrumpCard(rank, Suit.CLOVER));
        }

        return trumpCards;
    }

    private static List<TrumpCard> createDiamondCards() {
        List<TrumpCard> trumpCards = new ArrayList<>();

        for (Rank rank : Rank.values()) {
            trumpCards.add(new TrumpCard(rank, Suit.DIAMOND));
        }

        return trumpCards;
    }
}
