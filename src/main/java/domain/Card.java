package domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Card {

    private static final Map<String, Card> CARD_CACHE = new HashMap<>();

    private final Rank rank;
    private final Suit suit;

    private Card(Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public static Card of(Rank rank, Suit suit) {
        String cardKey = rank.getText() + "-" + suit.getName();
        return CARD_CACHE.computeIfAbsent(cardKey, key -> new Card(rank, suit));
    }

    public boolean isAceCard() {
        return this.rank.isAce();
    }

    public int getRankValue() {
        return rank.getValue();
    }

    public String getRankText() {
        return rank.getText();
    }

    public String getSuit() {
        return suit.getName();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        var that = (Card) obj;
        return Objects.equals(this.rank, that.rank) &&
                Objects.equals(this.suit, that.suit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rank, suit);
    }

}
