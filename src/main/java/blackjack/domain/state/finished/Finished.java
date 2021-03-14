package blackjack.domain.state.finished;

import blackjack.domain.Hand;
import blackjack.domain.participant.Dealer;
import blackjack.domain.state.State;

import java.util.Arrays;
import java.util.function.Function;

public abstract class Finished implements State {

    @Override
    public State update(Hand hand) {
        throw new IllegalStateException();
    }

    @Override
    public State stay() {
        throw new IllegalStateException();
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public double profitRate(Dealer dealer, int score) {
        return getResultType(dealer, score).getProfitRate();
    }

    abstract ResultType getResultType(Dealer dealer, int score);

    public ResultType getResultTypeByDifference(int difference) {
        return Arrays.stream(ResultType.values())
                .filter(resultType -> resultType.matcher.apply(difference))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("불가능한 결과입니다."));
    }

    enum ResultType {
        BLACKJACK((difference) -> difference > 0, 1.5),
        WIN((difference) -> difference > 0, 1),
        LOSE((difference) -> difference < 0, -1),
        TIE((difference) -> difference == 0, 0);

        private final Function<Integer, Boolean> matcher;
        private final double profitRate;

        ResultType(Function<Integer, Boolean> matcher, double profitRate) {
            this.matcher = matcher;
            this.profitRate = profitRate;
        }

        public double getProfitRate() {
            return profitRate;
        }
    }
}
