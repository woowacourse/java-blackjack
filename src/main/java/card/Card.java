package card;

public class Card {
    private final CardNumber cardNumber;
    private final Pattern pattern;

    public Card(CardNumber cardNumber, Pattern pattern) {
        this.cardNumber = cardNumber;
        this.pattern = pattern;
    }

    public String getName() {
        return cardNumber.getLabel() + pattern.getValue();
    }

    public int getScore() {
        return cardNumber.getValue();
    }
}
