package game;

import constant.TrumpEmblem;
import constant.TrumpNumber;

public class Card {

    private final TrumpNumber number;
    private final TrumpEmblem emblem;

    public Card(TrumpNumber number, TrumpEmblem emblem) {
        this.number = number;
        this.emblem = emblem;
    }

    public TrumpNumber getNumber() {
        return number;
    }

    public TrumpEmblem getEmblem() {
        return emblem;
    }
}
