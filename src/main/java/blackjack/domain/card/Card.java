package blackjack.domain.card;

public class Card {

    private CardSymbol cardSymbol;
    private CardType cardType;

    public Card(CardSymbol cardSymbol, CardType cardType) {
        this.cardSymbol = cardSymbol;
        this.cardType = cardType;
    }

    public boolean isAce() {
        return cardSymbol.isAce();
    }

    public int getCardNumber() {
        return cardSymbol.getCardNumber();
    }

    public String getCardSymbol() {
        return cardSymbol.getCardSymbol();
    }

    public CardType getCardType() {
        return cardType;
    }

    @Override
    public String toString() {
        return cardSymbol.getCardSymbol() + cardType.getKoreanName();
    }
}
