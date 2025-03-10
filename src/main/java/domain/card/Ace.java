package domain.card;

public class Ace extends Card {

    public Ace(final Suit suit) {
        super(Denomination.ACE, suit);
    }

    public void setValueToOne() {
        value = 1;
    }
}
