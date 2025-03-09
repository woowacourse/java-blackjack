package domain;

import domain.constant.TrumpEmblem;
import domain.constant.TrumpNumber;

public class Card {

    private final TrumpNumber number;
    private final TrumpEmblem emblem;

    public Card(TrumpNumber number, TrumpEmblem emblem) {
        this.number = number;
        this.emblem = emblem;
    }

    public boolean isAce() {
        return number == TrumpNumber.ACE;
    }

    public TrumpNumber getNumber() {
        return number;
    }

    public TrumpEmblem getEmblem() {
        return emblem;
    }
}
