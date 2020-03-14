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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return cardNumber == card.cardNumber &&
                cardFigure == card.cardFigure;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardNumber, cardFigure);
    }

    public boolean isAce() {
        return this.cardFigure.equals(CardNumber.ACE);
    }
//    public boolean has(CardNumber number) {
//        Objects.requireNonNull(number, String.format(NULL_ERROR_MSG, "카드 번호"));
//        return this.number == number;
//    }
//
//    public boolean has(CardNumber number, CardFigure cardFigure) {
//        Objects.requireNonNull(cardFigure, String.format(NULL_ERROR_MSG, "카드 모양"));
//        return has(number) && this.cardFigure == cardFigure;
//    }

    public CardFigure getCardFigure() {
        return cardFigure;
    }

    public CardNumber getCardNumber() {
        return cardNumber;
    }
}
