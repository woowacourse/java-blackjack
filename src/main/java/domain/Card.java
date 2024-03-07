package domain;

public class Card {
    private final CardType cardType;
    private final CardNumber cardNubmer;

    public Card(CardType cardType, CardNumber cardNumber) {
        validateNull(cardType, cardNumber);

        this.cardType = cardType;
        this.cardNubmer = cardNumber;
    }

    private void validateNull(CardType cardType, CardNumber cardNumber) {
        if (cardType == null || cardNumber == null) {
            throw new IllegalArgumentException("null을 인자로 받을 수 없습니다.");
        }
    }

    public boolean isAce() {
        return cardNubmer.equals(CardNumber.ACE);
    }

    public CardType getCardType() {
        return cardType;
    }

    public CardNumber getCardNumber() {
        return cardNubmer;
    }
}
