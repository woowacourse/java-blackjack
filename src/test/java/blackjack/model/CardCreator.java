package blackjack.model;

public class CardCreator {

    public static Card createCard(final CardNumber cardNumber) {
        return new Card(CardType.CLOVER, cardNumber);
    }

}
