package domain;

import constant.Rank;
import constant.Suit;

public class Card {

    private final Rank Rank;
    private final Suit suit;

    public Card(constant.Rank rank, Suit suit) {
        Rank = rank;
        this.suit = suit;
    }
}
