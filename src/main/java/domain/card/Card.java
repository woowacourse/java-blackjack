package domain.card;

import java.util.List;

public class Card {

    private final Denomination denomination;
    private final Suits suit;

    public Card(Denomination denomination, Suits suit) {
        this.denomination = denomination;
        this.suit = suit;
    }

    public Score getScore() {
        return denomination.getScore();
    }

    public Denomination getDenomination() {
        return denomination;
    }

    public Suits getSuit() {
        return suit;
    }

    public String getCardName() {
        return denomination.getPoint() + suit.getName();
    }
}
