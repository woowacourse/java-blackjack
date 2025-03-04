package domain;

import constant.Emblem;

public record Card(
        int number,
        Emblem emblem
) {
    public Card(final int number, final Emblem emblem) {
        this.number = number;
        this.emblem = emblem;
    }
}
