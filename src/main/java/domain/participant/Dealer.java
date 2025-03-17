package domain.participant;

import static domain.BlackjackGame.BLACKJACK_SCORE;
import static domain.BlackjackGame.DEALER_MIN_SCORE;
import static domain.BlackjackGame.INITIAL_CARDS;

import domain.BlackjackGame;
import domain.card.Hand;

public class Dealer extends CardOwner {

    private Dealer() {
        super(Hand.of());
    }

    public static Dealer of() {
        return new Dealer();
    }

    public boolean isBust() {
        return calculateScore() > BlackjackGame.BLACKJACK_SCORE;
    }

    public boolean isBlackjack() {
        return countCard() == INITIAL_CARDS && calculateScore() == BLACKJACK_SCORE;
    }

    @Override
    public boolean canReceive() {
        return (calculateScore() <= DEALER_MIN_SCORE);
    }
}
