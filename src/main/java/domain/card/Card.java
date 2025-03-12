package domain.card;

public record Card(Shape shape, Rank rank) {

    public int getValue() {
        return rank.getValue();
    }

    public boolean isAce() {
        return rank == Rank.ACE;
    }

}
