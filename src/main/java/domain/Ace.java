package domain;

public class Ace extends Card {

    public Ace(Denomination denomination, Suit suit) {
        super(denomination, suit);
    }

    public void setValueToOne() {
        value = 1;
    }
}
