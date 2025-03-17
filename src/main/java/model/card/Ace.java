package model.card;

public class Ace extends Card {

    public Ace(Suit suit) {
        super(Denomination.ACE, suit);
    }

    public void setValueToOne() {
        value = 1;
    }
}
