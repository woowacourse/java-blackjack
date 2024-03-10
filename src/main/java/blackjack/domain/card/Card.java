package blackjack.domain.card;

import java.util.Objects;

public class Card {

    // TODO: final 추가 및 cardShape, cardNumber 제거
    private CardShape cardShape;
    private CardNumber cardNumber;
    private CardRank cardRank;
    private CardSuit cardSuit;

    public Card(CardShape cardShape, CardNumber cardNumber) {
        this.cardShape = cardShape;
        this.cardNumber = cardNumber;
    }

    public Card(CardRank cardRank, CardSuit cardSuit) {
        this.cardRank = cardRank;
        this.cardSuit = cardSuit;
    }

    public boolean isAce() {
        return cardRank.isAce();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Card card = (Card) o;
        return cardSuit == card.cardSuit && cardRank == card.cardRank;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardSuit, cardRank);
    }

    public int getCardNumber() {
        return cardNumber.getValue();
    }

    public String getCardNumberName() {
        return cardNumber.getName();
    }

    public String getCardShape() {
        return cardShape.getName();
    }

    public CardRank getCardRank() {
        return cardRank;
    }

    public CardSuit getCardSuit() {
        return cardSuit;
    }
}
