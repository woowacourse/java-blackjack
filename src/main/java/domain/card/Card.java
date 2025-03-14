package domain.card;

public record Card(Rank rank, Shape shape) {

    public int getNumber() {
        return rank.number();
    }
}
