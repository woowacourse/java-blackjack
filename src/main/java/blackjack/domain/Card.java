package blackjack.domain;

public class Card {

    private final CardValue value;
    private final CardShape cardShape;

    public Card(CardValue value, CardShape cardShape) {
        this.value = value;
        this.cardShape = cardShape;
    }

    public boolean isAce() {
        return value == CardValue.ACE;
    }

    public String getName() {
        return value.getName() + cardShape.getName();
    }

    public int getValue() {
        return value.getValue();
    }

}
