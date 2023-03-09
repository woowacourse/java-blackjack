package domain;

import java.util.List;

import static domain.Status.*;

public abstract class User {

    private static final Score BLACKJACK_SCORE = new Score(21);

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

    private boolean isBusted() {
        return score().isGreaterThan(BLACKJACK_SCORE);
    }

    private boolean isBlackjack() {
        return hand.size() == 2 && score().equals(BLACKJACK_SCORE);
    }

    void addCard(Card card) {
        hand.add(card);
        updateStatus();
    }

    private void updateStatus() {
        if (isBusted()) {
            status = BUST;
        }

        if (!canHit()) {
            status = STAY;
        }

        if (isBlackjack()) {
            status = BLACKJACK;
        }
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


