package blackjack.domain;

public record Card(Rank rank, Shape shape) {

    public String getDisplayName() {
        return rank.getName() + shape.getName();
    }

    public int translateToScore() {
        return rank.getScore();
    }

    public boolean isAce() {
        return rank == Rank.ACE;
    }

}
