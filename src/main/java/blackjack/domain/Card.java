package blackjack.domain;

public class Card {

    private final CardShape cardShape;
    private final CardNumber number;

    public Card(final CardShape cardShape, final CardNumber number) {
        this.cardShape = cardShape;
        this.number = number;
    }

    public CardShape getShape(){
        return cardShape;
    }

    public CardNumber getNumber(){
        return number;
    }
}
