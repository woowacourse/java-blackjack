package blackjack.model.card;

public record BlackJackCard(CardType cardType, CardNumber cardNumber) {

    public int getValue() {
        return cardNumber.getValue();
    }
}
