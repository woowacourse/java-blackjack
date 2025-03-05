package domain;

import java.util.Objects;

import constant.CardNumber;
import constant.Emblem;

public record Card(
    CardNumber number,
    Emblem emblem
) {

    public boolean isMatchNumber(final CardNumber cardNumber) {
        return Objects.equals(number, cardNumber);
    }

    public int sumNumber(final int score) {
        return number.sum(score);
    }
}
