package blackjack.domain.card;

public record Card(Suit suit, Rank rank) {

    public String toDisplayName() {
        return rank.getName() + suit.getName();
    }

    public int getValue() {
        return rank.getValue();
    }

    public boolean isAce() {
        return rank.isAce();
    }
}
