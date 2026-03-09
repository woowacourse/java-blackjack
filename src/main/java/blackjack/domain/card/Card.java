package blackjack.domain.card;

public record Card(Suit suit, Rank rank) {

    public String getDisplayName() {
        return rank.getName() + suit.getDisplayName();
    }

    public int getValue() {
        return rank.getValue();
    }

    public boolean isAce() {
        return rank.isAce();
    }
}
