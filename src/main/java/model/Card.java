package model;

public class Card {
    private final CardShape shape;
    private final CardValue value;

    public Card(CardShape shape, CardValue value) {
        this.shape=shape;
        this.value=value;
    }

    public String getCardInfo() {
        return this.value.getScore()+this.shape.getName();
    }
}
