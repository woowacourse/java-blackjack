package blackjack.domain.participant;

import blackjack.domain.vo.Result;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class Dealer extends Participant {

    private static final int MAXIMUM_SCORE_FOR_ADDITIONAL_CARD = 16;
    private static final long DEFAULT_VALUE = 0L;
    private static final String DEALER_NAME = "딜러";
    private static final int INCREASE_COUNT = 1;

    public Dealer() {
        super(DEALER_NAME);
    }

    @Override
    public boolean isAbleToReceiveCard() {
        int score = calculateFinalScore();
        return score <= MAXIMUM_SCORE_FOR_ADDITIONAL_CARD;
    }

    public Map<Result, Long> getStatisticResult(List<Player> players) {
        Map<Result, Long> statisticResult = new EnumMap<>(Result.class);
        for (Result result : Result.values()) {
            statisticResult.put(result, DEFAULT_VALUE);
        }
        for (Player player : players) {
            Result result = player.judgeResult(this);
            Result replacedResult = replaceWinWithLose(result);
            statisticResult.put(replacedResult,
                    statisticResult.getOrDefault(replacedResult, DEFAULT_VALUE) + INCREASE_COUNT);
        }
        return statisticResult;
    }

    private Result replaceWinWithLose(Result result) {
        if (result == Result.LOSE) {
            return Result.WIN;
        }
        if (result == Result.WIN) {
            return Result.LOSE;
        }
        return Result.DRAW;
    }
}
