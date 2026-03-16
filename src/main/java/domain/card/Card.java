package domain.card;

public record Card(Suit suit, Rank rank) {

    public int getScore() {
        return rank.getScore();
    }

    public boolean isAce() {
        return rank == Rank.ACE;
    }

    public String getCardName() {
        return rank.getDescription() + suit.getDescription();
    }
}