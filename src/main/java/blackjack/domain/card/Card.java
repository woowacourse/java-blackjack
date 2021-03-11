package blackjack.domain.card;

public class Card {
    private final CardNumber cardValue;
    private final CardType cardType;

    public Card(final CardNumber cardNumber, final CardType cardType) {
        this.cardValue = cardNumber;
        this.cardType = cardType;
    }

    public String getCardSymbol() {
        return cardValue.getSymbol();
    }

    public CardNumber getCardValue() {
        return cardValue;
    }

    public String getCardType() {
        return cardType.getType();
    }

    public static boolean isAce(Card card) {
        return card.cardValue.isAce();
    }
}
