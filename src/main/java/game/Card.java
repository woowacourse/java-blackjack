package game;

import constant.Suit;
import constant.Rank;

public class Card {

    private final Rank number;
    private final Suit emblem;

    public Card(Rank number, Suit emblem) {
        this.number = number;
        this.emblem = emblem;
    }

    public Rank getNumber() {
        return number;
    }

    public Suit getEmblem() {
        return emblem;
    }
}
