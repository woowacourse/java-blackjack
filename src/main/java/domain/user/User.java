package domain.user;

import static domain.Status.BLACKJACK;
import static domain.Status.BUST;
import static domain.Status.PLAYING;
import static domain.Status.STAY;

import domain.Score;
import domain.Status;
import domain.card.Card;
import domain.card.Hand;
import java.util.List;

public abstract class User {
    public static int NUMBER_OF_FIRST_CARDS = 2;
    public static final Score BLACKJACK_SCORE = new Score(21);

    private final Hand hand;
    private Status status;

    public User(Hand hand) {
        this.hand = hand;
        this.status = PLAYING;
    }

    abstract boolean canHit();

    public abstract String getName();

    public void addCard(Card card) {
        hand.add(card);
        updateStatus();
    }

    private void updateStatus() {
        if (!canHit()) {
            status = STAY;
        }

        if (score().isBusted()) {
            status = BUST;
        }

        if (isBlackjack()) {
            status = BLACKJACK;
        }
    }

    public void updateStatusToStay(boolean isYes) {
        if (!isYes) {
            status = STAY;
        }
    }

    private boolean isBlackjack() {
        return hand.size() == NUMBER_OF_FIRST_CARDS
                && score().equals(BLACKJACK_SCORE);
    }

    public Score score() {
        return hand.calculateScore();
    }

    public Status status() {
        return status;
    }

    public List<Card> cards() {
        return hand.cards();
    }
}


