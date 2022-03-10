package blackjack.domain;

public class Card {
    private final CardShape shape;
    private final CardNumber number;
    private boolean isOpen = true;

    public Card(CardShape shape, CardNumber number) {
        this.shape = shape;
        this.number = number;
    }

    public CardShape getCardShape() {
        return this.shape;
    }

    public CardNumber getCardNumber() {
        return this.number;
    }

    public void close() {
        isOpen = false;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void open() {
        isOpen = true;
    }

    public boolean isA() {
        return number.isA();
    }
}
