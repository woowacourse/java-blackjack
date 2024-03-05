package model;

public class Card {

    private final Number number;
    private final Emblem emblem;

    public Card(Number number, Emblem emblem) {
        this.number = number;
        this.emblem = emblem;
    }

    public int getNumberValue() {
        return number.getValue();
    }

    @Override
    public String toString() {
        return number.getSignature() + emblem.getValue();
    }

}
