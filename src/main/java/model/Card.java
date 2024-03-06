package model;


import java.util.Objects;

public class Card {

    private final Number number;
    private final Emblem emblem;

    Card(Number number, Emblem emblem) {
        this.number = number;
        this.emblem = emblem;
    }

    public static Card from(Number number, Emblem emblem) {
        if (number == Number.ACE) {
            return new AceCard(number, emblem);
        }
        return new Card(number, emblem);
    }

    public int getCardActualValue() {
        return number.getValue();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Card card)) {
            return false;
        }
        return number == card.number && emblem == card.emblem;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, emblem);
    }

    @Override
    public String toString() {
        return number.getSignature() + emblem.getValue();
    }

}
