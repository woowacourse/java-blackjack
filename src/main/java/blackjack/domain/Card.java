package blackjack.domain;

public class Card {

    private final Shape shape;
    private final CardNumber number;

    public Card(final Shape shape, final CardNumber number) {
        this.shape = shape;
        this.number = number;
    }

    public Shape getShape(){
        return shape;
    }

    public CardNumber getNumber(){
        return number;
    }
}
