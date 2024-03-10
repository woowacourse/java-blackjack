package domain;

import domain.constant.CardNumber;
import domain.constant.CardType;

public class Card {
    private final CardType cardType;
    private final CardNumber cardNumber;

    public Card(CardType cardType, CardNumber cardNumber) {
        validateNull(cardType, cardNumber);
        this.cardType = cardType;
        this.cardNumber = cardNumber;
    }

    private void validateNull(CardType cardType, CardNumber cardNumber) {
        if (cardType == null || cardNumber == null) {
            throw new IllegalArgumentException("null을 인자로 받을 수 없습니다.");
        }
    }

    public boolean isAce() {
        return cardNumber.equals(CardNumber.ACE);
    }

    public CardType getCardType() {
        return cardType;
    }

    public CardNumber getCardNumber() {
        return cardNumber;
    }

    @Override
    public String toString() {
        return cardNumber.getSymbol() + cardType.getType();
    }
}
