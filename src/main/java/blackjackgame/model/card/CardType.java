package blackjackgame.model.card;

public record CardType(String card) {
    public CardType(Card card) {
        this(card.cardTypeLettering());
    }
}
