package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hand;
import blackjack.domain.game.ResultType;
import blackjack.domain.game.Score;

public abstract class Participant {

    private final String name;
    private final Hand hand;

    protected Participant(final String name, final Hand hand) {
        this.name = name;
        this.hand = hand;
    }

    public abstract void receiveCard(Card card);

    public abstract boolean canReceive();

    public Score getCurrentScore() {
        return Score.calculateSumFrom(hand);
    }

    public String getName() {
        return name;
    }

    public Hand getHand() {
        return hand;
    }

    // TODO: 구현체의 canReceive 메소드와 상당히 겹침. 해결 필요.
    public boolean isBusted() {
        return Score.calculateSumFrom(hand).isBusted();
    }

    public ResultType compareWith(Participant other) {
        if (isWin(other)) {
            return ResultType.WIN;
        }

        if (isLose(other)) {
            return ResultType.LOSE;
        }

        return ResultType.DRAW;
    }

    private boolean isWin(Participant other) {
        int playerScore = getCurrentScore().getValue();
        int otherScore = other.getCurrentScore().getValue();

        boolean isGreaterThanOtherAndNotBusted = playerScore > otherScore && !isBusted();
        boolean isNotBustedButOtherIsBusted = !isBusted() && other.isBusted();

        return isGreaterThanOtherAndNotBusted || isNotBustedButOtherIsBusted;
    }

    private boolean isLose(Participant other) {
        int playerScore = getCurrentScore().getValue();
        int otherScore = other.getCurrentScore().getValue();

        boolean isLessThanOtherAndOtherIsNotBusted = playerScore < otherScore && !other.isBusted();
        boolean isBustedButOtherIsNotBusted = isBusted() && !other.isBusted();

        return isLessThanOtherAndOtherIsNotBusted || isBustedButOtherIsNotBusted;
    }
}
