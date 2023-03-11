package domain.user;

import domain.Result;
import domain.Score;
import domain.Status;
import domain.card.Card;
import domain.card.Hand;

import java.util.List;

import static domain.Status.*;

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

    public Result compare(User other) {
        if (isDraw(other)) {
            return Result.DRAW;
        }
        if (isWon(other)) {
            return Result.WIN;
        }
        return Result.LOSE;
    }

    private boolean isWon(User other) {
        if (status == BUST) {
            return false;
        }

        return score().isGreaterThan(other.score()) || other.status == BUST;
    }

    private boolean isDraw(User other) {
        if (status == BUST && other.status == BUST) {
            return true;
        }

        return score().equals(other.score());
    }

    public void addCard(Card card) {
        hand.add(card);
        updateStatus();
    }

    private void updateStatus() {
        if (score().isBusted()) {
            status = BUST;
            return;
        }

        if (!canHit()) {
            status = STAY;
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
        return hand.score();
    }

    public Status status() {
        return status;
    }

    public List<Card> cards() {
        return hand.getCards();
    }

}


