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
        return !isBusted() && (score().getValue() > other.score().getValue() || other.isBusted());
    }

    private boolean isDraw(User other) {
        return score().getValue() == other.score().getValue() || (isBusted() && other.isBusted());
    }

    boolean isBusted() {
        return score().isGreaterThan(BLACKJACK_SCORE);
    }

    boolean isBlackjack() {
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
        if (isBusted()) {
            return BUST;
        }
        if (!canHit()) {
            return STAY;
        }
        return status;
    }

    public void updateStatusToStay() {
        status = STAY;
    }

    public List<Card> cards() {
        return hand.getCards();
    }

}


