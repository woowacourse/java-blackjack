package domain.card;

import java.util.Objects;

public class Card {
    private final CardNumber cardNumber;
    private final CardSymbol cardSymbol;

    public Card(final CardNumber cardNumber, final CardSymbol cardSymbol) {
        this.cardNumber = cardNumber;
        this.cardSymbol = cardSymbol;
    }

}
