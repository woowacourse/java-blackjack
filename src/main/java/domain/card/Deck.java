package domain.card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Deck {

    private static final int FRONT_CARD_INDEX = 0;

    private final List<Card> cards;

    private Deck(List<Card> cards) {
        this.cards = cards;
    }

    public static Deck createDeckForBlackJack() {
        return new Deck(createCardsForBlackJack());
    }

    private static List<Card> createCardsForBlackJack() {
        List<Card> cards = new ArrayList<>();
        gatherCardsForBlackJack(cards);
        Collections.shuffle(cards);
        return cards;
    }

    private static void gatherCardsForBlackJack(List<Card> cards) {
        for (Rank rank : Rank.values()) {
            cards.addAll(createCardsByRank(rank));
        }
    }

    private static List<Card> createCardsByRank(Rank rank) {
        return Arrays.stream(Suit.values())
                .map(suit -> new Card(rank, suit))
                .collect(Collectors.toList());
    }

    public Card draw() {
        Card card = cards.get(FRONT_CARD_INDEX);
        cards.remove(FRONT_CARD_INDEX);
        return card;
    }
}
