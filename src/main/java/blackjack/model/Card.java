package blackjack.model;

public record Card(
        Rank rank,
        Suit suit
) {

    @Override
    public String toString() {
        return rank.getDisplayName() + suit.getDisplayName();
    }
}
