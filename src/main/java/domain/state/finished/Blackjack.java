package domain.state.finished;

import domain.card.Hand;
import domain.state.Result;
import domain.state.State;
import java.util.function.Function;

public class Blackjack extends Finished {
    private static final int HAND_SIZE = 2;
    private static final int BLACKJACK_SCORE = 21;

    public Blackjack(Hand hand) {
        super(hand);
    }

    public static boolean isBlackJack(Hand hand) {
        return hand.getSize() == HAND_SIZE && hand.getScore() == BLACKJACK_SCORE;
    }

    @Override
    public Function<Integer, Integer> earningRate(Result result) {
        if (Result.LOSE.equals(result)) {
            throw new IllegalStateException("Blackjack은 질수 없습니다.");
        }
        if (Result.DRAW.equals(result)) {
            return (n) -> 0;
        }
        return (n) -> n * 15 / 10;
    }

    @Override
    public Result getResult(State dealerState) {
        if (dealerState instanceof Blackjack) {
            return Result.DRAW;
        }
        return Result.WIN;
    }
}
