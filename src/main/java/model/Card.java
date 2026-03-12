package model;

public record Card(CardShape shape, CardValue value) {

    public boolean isAce() {
        return value == CardValue.ACE;
    }

    public int score() {
        return value.score();
    }
}
