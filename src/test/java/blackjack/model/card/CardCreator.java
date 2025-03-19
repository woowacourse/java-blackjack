package blackjack.model.card;

public class CardCreator {

    public static BlackJackCard createCard(final CardNumber cardNumber) {
        return new BlackJackCard(CardType.CLOVER, cardNumber);
    }

}
