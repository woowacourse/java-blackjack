package card;

public class Card {

    private final CardValue cardValue;

    public Card(final CardValue cardValue) {
        this.cardValue = cardValue;
    }

    public CardValue cardValue() {
        return this.cardValue;
    }
}
