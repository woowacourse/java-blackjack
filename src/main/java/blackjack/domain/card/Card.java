package blackjack.domain.card;

public class Card {

    private CardSymbol cardSymbol;
    private CardType cardType;

    public Card(CardSymbol cardSymbol, CardType cardType) {
        this.cardSymbol = cardSymbol;
        this.cardType = cardType;
    }

    @Override
    public String toString() {
        return cardSymbol.getCardSymbol() + cardType.getKoreanName();
    }
}
