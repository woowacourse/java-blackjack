package blackjack.domain.card;

public class Card {
    public static final int ACE_SCORE = 1;
    public static final int TEN_SCORE = 10;


    private final Suit suit;
    private final CardNumber cardNumber;

    public Card(Suit suit, CardNumber cardNumber) {
        this.suit = suit;
        this.cardNumber = cardNumber;
    }

    public int getScore() {
        return cardNumber.getScore();
    }

    public boolean isAce() {
        return cardNumber.getScore() == ACE_SCORE;
    }

    public boolean isTen() {
        return cardNumber.getScore() == TEN_SCORE;
    }

    @Override
    public String toString() {
        return this.cardNumber.getScore() + this.suit.getName();
    }
}
