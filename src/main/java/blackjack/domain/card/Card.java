package blackjack.domain.card;

public class Card {
    
    private final CardNumber number;
    private final CardShape shape;

    public Card(final CardNumber number, final CardShape shape) {
        this.number = number;
        this.shape = shape;
    }


    public CardNumber getNumber() {
        return number;
    }

    public CardShape getShape() {
        return shape;
    }
}
