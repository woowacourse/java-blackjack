package model.card;

public record CardType(String card) {
    public CardType(Card card) {
        this(card.getNumber().getNumber() + card.getShape().getShape());
    }
}
