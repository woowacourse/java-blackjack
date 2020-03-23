package blackjack.domain.card;

import blackjack.domain.card.component.CardFigure;
import blackjack.domain.card.component.CardNumber;

import java.util.Objects;

public class Card {
    private static final String NULL_ERROR_MSG = "%s이(가) 없습니다.";
    private CardNumber cardNumber;
    private CardFigure cardFigure;

    public Card(CardNumber cardNumber, CardFigure cardFigure) {
        Objects.requireNonNull(cardNumber, String.format(NULL_ERROR_MSG, "카드 번호"));
        Objects.requireNonNull(cardFigure, String.format(NULL_ERROR_MSG, "카드 모양"));

        this.cardNumber = cardNumber;
        this.cardFigure = cardFigure;
    }


    public boolean isAce() {
        return cardNumber.equals(CardNumber.ACE);
    }

    public String getCardFigure() {
        return cardFigure.getMessage();
    }

    public String  getCardNumber() {
        return cardNumber.getMessage();
    }

    public int getCardPoint() {
        return cardNumber.getNumber();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return cardNumber.equals(card.cardNumber) &&
                cardFigure.equals(card.cardFigure);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardNumber, cardFigure);
    }
}
