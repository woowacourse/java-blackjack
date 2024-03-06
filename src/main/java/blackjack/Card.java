package blackjack;

public class Card {
    private CardValue cardValue;
    private CardSymbol cardSymbol;

    public Card(CardValue cardValue, CardSymbol cardSymbol) {
        this.cardValue = cardValue;
        this.cardSymbol = cardSymbol;
    }

    public int getCardValue() {
        return cardValue.getValue();
    }
    
    public boolean isAce() {
        return cardValue.isAce();
    }
}
