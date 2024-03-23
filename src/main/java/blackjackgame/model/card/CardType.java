package blackjackgame.model.card;

public class CardType {

    private final String cardType;

    public CardType(Card card) {
        this.cardType = card.cardTypeLettering();
    }

    public String getCardType() {
        return cardType;
    }
}
