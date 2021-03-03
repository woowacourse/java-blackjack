package blackjack.domain;

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
        int currentScore = getFinalScore();
        return currentScore <= MAXIMUM_SCORE_FOR_ADDITIONAL_CARD;
    }

    public Map<Result, Long> getStatisticResult(List<Player> players) {
        Map<Result, Long> statisticResult = players.stream()
                                                   .map(player -> player.judgeResult(this))
                                                   .collect(Collectors.groupingBy(
                                                       result -> result,
                                                       () -> new EnumMap<>(Result.class),
                                                       Collectors.counting()));
        long lossCounts = statisticResult.getOrDefault(Result.LOSE, DEFAULT_VALUE);
        long winCounts = statisticResult.getOrDefault(Result.WIN, DEFAULT_VALUE);
        long drawCounts = statisticResult.getOrDefault(Result.DRAW, DEFAULT_VALUE);
        statisticResult.put(Result.WIN, lossCounts);
        statisticResult.put(Result.LOSE, winCounts);
        statisticResult.put(Result.DRAW, drawCounts);
        return statisticResult;
    }
}
