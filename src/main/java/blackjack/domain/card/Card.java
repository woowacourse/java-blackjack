package blackjack.domain.card;

public record Card(Shape shape, Value value) {
    public int getScore() {
        return value().getScore();
    }
}
