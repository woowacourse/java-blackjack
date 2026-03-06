package blackjack.domain;

public class Card {

    private CardValue value;
    private CardShape cardShape;

    public Card(CardValue value, CardShape cardShape) {
        this.value = value;
        this.cardShape = cardShape;
    }

    public boolean isAce() {
        return value == CardValue.A;
    }

    public String getName() {
        return value.getName() + cardShape.getName();
    }

    public int getValue() {
        return value.getValue();
    }

}
