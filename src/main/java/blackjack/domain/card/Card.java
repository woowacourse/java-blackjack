package blackjack.domain.card;

public class Card {

    private final CardPattern cardPattern;
    private final CardNumber cardNumber;

    public Card(CardPattern cardPattern, CardNumber cardNumber) {
        this.cardPattern = cardPattern;
        this.cardNumber = cardNumber;
    }

    public boolean isAceCard() {
        return cardNumber == CardNumber.ACE;
    }

    public String getCardPattern() {
        return cardPattern.getPattern();
    };

    public String getCardNumberType() {
        return cardNumber.getType();
    }

    public int getCardNumber() {
        return cardNumber.getValue();
    }
}
