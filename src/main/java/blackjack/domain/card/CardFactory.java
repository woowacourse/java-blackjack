package blackjack.domain.card;

import java.util.ArrayList;
import java.util.List;

public class CardFactory {

    private CardFactory() {
    }

    public static List<Card> createCards() {
        List<Card> cards = new ArrayList<>();

        cards.addAll(createSpadeCards());
        cards.addAll(createDiamondCards());
        cards.addAll(createHeartCards());
        cards.addAll(createCloverCards());

        return cards;
    }

    private static List<Card> createHeartCards() {
        List<Card> cards = new ArrayList<>();

        for (Rank rank : Rank.values()) {
            cards.add(new Card(rank, Suit.HEART));
        }

        return cards;
    }

    private static List<Card> createSpadeCards() {
        List<Card> cards = new ArrayList<>();

        for (Rank rank : Rank.values()) {
            cards.add(new Card(rank, Suit.SPADE));
        }

        return cards;
    }

    private static List<Card> createCloverCards() {
        List<Card> cards = new ArrayList<>();

        for (Rank rank : Rank.values()) {
            cards.add(new Card(rank, Suit.CLOVER));
        }

        return cards;
    }

    private static List<Card> createDiamondCards() {
        List<Card> cards = new ArrayList<>();

        for (Rank rank : Rank.values()) {
            cards.add(new Card(rank, Suit.DIAMOND));
        }

        return cards;
    }
}
