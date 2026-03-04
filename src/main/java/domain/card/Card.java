package domain.card;

public record Card(Suit suit, Rank rank) {

    public String getCardName() {
        return rank.getDescription() + suit.getDescription();
    }
}