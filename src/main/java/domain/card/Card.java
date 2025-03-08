package domain.card;

public record Card(Rank rank, Shape shape) {

    public int getNumber() {
        return rank.getNumber();
    }

    @Override
    public String toString() {
        return rank.getName() + shape.getName();
    }
}
