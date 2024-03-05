package blackjack.domain;

public class Card {
    private final CardNumber number;
    private final CardShape shape;

    public Card(final CardNumber number, final CardShape shape) {
        this.number = number;
        this.shape = shape;
    }

    public boolean isAce() {
        return number.equals(CardNumber.ACE);
    }

    public int getRealNumber() {
        return number.getNumber();
    }
}
