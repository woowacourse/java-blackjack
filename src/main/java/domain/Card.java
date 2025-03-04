package domain;

public record Card(
        int number,
        String emblem
) {
    public Card(final int number, final String emblem) {
        this.number = number;
        this.emblem = emblem;
    }
}
