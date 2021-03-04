package blackjack.domain.card;

public class Card {
    public static final int BLACKJACK_SCORE = Integer.MAX_VALUE;
    public static final int BUST = Integer.MIN_VALUE;
    private static final int ACE_SCORE = 1;

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

    @Override
    public String toString() {
        return this.cardNumber.getScore() + this.suit.getName();
    }
}
