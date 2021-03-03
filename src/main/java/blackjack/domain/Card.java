package blackjack.domain;

public class Card {
    private final CardType cardType; //pattern
    private final CardValue cardValue; // 끝 수

    public Card(CardType cardType, CardValue cardValue) {
        this.cardType = cardType;
        this.cardValue = cardValue;
    }

    public boolean isAce() {
        return this.cardValue == CardValue.ACE;
    }

    public int getPoint() {
        return cardValue.getPoint();
    }
}
