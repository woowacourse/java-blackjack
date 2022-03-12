package blackjack.dto;

import blackjack.model.card.Card;
import blackjack.model.card.TrumpNumber;
import blackjack.model.card.TrumpSymbol;

public class CardDto {
    private final TrumpNumber trumpNumber;
    private final TrumpSymbol trumpSymbol;

    public CardDto(Card card) {
        trumpNumber = card.getNumber();
        trumpSymbol = card.getSymbol();
    }

    public String getNumber() {
        return trumpNumber.getValueOfString();
    }

    public String getSymbol() {
        return trumpSymbol.getValue();
    }
}
