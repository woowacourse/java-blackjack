package domain.card;

import domain.Rank;
import domain.Suit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Card {
    public static final String FIELD_CAN_NOT_BE_NULL = "[ERROR] 멤버 변수로 Null을 넣을 수 없습니다.";
    private static final Map<String, Card> CACHE;

    static {
        Map<String, Card> map = new HashMap<>();
        for (Suit suit : Suit.values()) {
            putCardsBySuit(map, suit);
        }
        CACHE = Map.copyOf(map);
    }

    private final Suit suit;
    private final Rank rank;

    private Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    private static void putCardsBySuit(Map<String, Card> map, Suit suit) {
        for (Rank rank : Rank.values()) {
            map.put(toKey(suit, rank), new Card(suit, rank));
        }
    }

    private static String toKey(Suit suit, Rank rank) {
        return suit.getValue() + rank.getDisplayValue();
    }


    public static Card of(Suit suit, Rank rank) {
        validate(suit, rank);
        return CACHE.get(toKey(suit, rank));
    }

    private static void validate(Suit suit, Rank rank) {
        if (suit == null || rank == null) {
            throw new IllegalArgumentException(FIELD_CAN_NOT_BE_NULL);
        }
    }

    public static List<Card> getAllTypesOfCard() {
        return new ArrayList<>(CACHE.values());
    }

    public boolean isAce() {
        return rank.isAce();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Card card = (Card) o;
        return suit == card.suit && rank == card.rank;
    }

    @Override
    public int hashCode() {
        return Objects.hash(suit, rank);
    }

    public Suit getSuit() {
        return suit;
    }

    public Rank getRank() {
        return rank;
    }
}
