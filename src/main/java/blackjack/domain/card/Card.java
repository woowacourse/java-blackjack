package blackjack.domain.card;

public class Card {
    private CardValue cardValue;
    private CardSymbol cardSymbol;

    public Card(CardValue cardValue, CardSymbol cardSymbol) {
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
