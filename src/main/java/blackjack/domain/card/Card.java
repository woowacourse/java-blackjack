package blackjack.domain.card;

public class Card {
    private final CardValue cardValue;
    private final CardSymbol cardSymbol;

    public Card(final CardValue cardValue, final CardSymbol cardSymbol) {
        this.cardValue = cardValue;
        this.cardSymbol = cardSymbol;
    }

    public boolean isAce() {
        return cardValue.isAce();
    }

    public int getCardScore() {
        return cardValue.getScore();
    }

    public CardValue getCardValue() {
        return cardValue;
    }

    public CardSymbol getCardSymbol() {
        return cardSymbol;
    }
}
