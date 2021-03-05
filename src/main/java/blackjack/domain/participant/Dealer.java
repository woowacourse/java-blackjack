package blackjack.domain.participant;

import blackjack.domain.vo.Result;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Dealer extends Participant {
    private static final int MAXIMUM_SCORE_FOR_ADDITIONAL_CARD = 16;
    private static final long DEFAULT_VALUE = 0L;
    private static final String DEALER_NAME = "딜러";

    public Dealer() {
        super(DEALER_NAME);
    }

    @Override
    public boolean isAbleToReceiveCard() {
        int score = calculateFinalScore();
        return score <= MAXIMUM_SCORE_FOR_ADDITIONAL_CARD;
    }

    public Map<Result, Long> aggregateResultStatistics(List<Player> players) {
        Map<Result, Long> resultStatistics = players.stream()
                .map(player -> player.judgeResult(this))
                .collect(Collectors.groupingBy(this::replaceWinWithLose, () -> new EnumMap<>(Result.class), Collectors.counting()));
        initializeWhenKeyIsEmpty(resultStatistics);
        return resultStatistics;
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

    private void initializeWhenKeyIsEmpty(Map<Result, Long> resultStatistics) {
        Arrays.stream(Result.values())
                .forEach(result -> resultStatistics.putIfAbsent(result, DEFAULT_VALUE));
    }
}
