package blackjack.domain.card;

import blackjack.util.BlackJackConstant;

public class Card {
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
        return cardNumber.getScore() == BlackJackConstant.ACE_SCORE;
    }

    public boolean isTen() {
        return cardNumber.getScore() == BlackJackConstant.TEN_SCORE;
    }

    @Override
    public String toString() {
        return this.cardNumber.getScore() + this.suit.getName();
    }
}
