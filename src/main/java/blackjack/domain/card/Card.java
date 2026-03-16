package blackjack.domain.card;

public class Card {

    private final Suit suit;
    private final Rank rank;

    public Card(final Suit suit, final Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public boolean isAce() {
        return rank.isAce();
    }

    public String getDisplayName() {
        return rank.getName() + suit.getName();
    }

    public int getValue() {
        return rank.getValue();
    }
}
