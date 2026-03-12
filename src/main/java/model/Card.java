package model;

public record Card(CardShape shape, CardValue value) {

    public boolean isAce() {
        return value == CardValue.ACE;
    }

    public int getScore() {
        return value.score();
    }
}
