package blackJack.domain.Card;

public class Card {
    private Suit suit;
    private Denomination denomination;

    public Card(Suit suit, Denomination denomination) {
        this.suit = suit;
        this.denomination = denomination;
    }


    public boolean isAce() {
        return this.getNumber().equals(Denomination.ACE);
    }

    public Denomination getNumber() {
        return denomination;
    }

    public String getCardInfo() {
        return denomination.getDenomination() + suit.getShapeName();
    }
}
