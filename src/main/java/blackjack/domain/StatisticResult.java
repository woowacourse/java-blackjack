package blackjack.domain;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StatisticResult {

    private static final long DEFAULT_VALUE = 0L;
    private static final int INCREASE_COUNT = 1;
    private final Participants participants;
    private final Dealer dealer;
    private final List<Player> players;

    public StatisticResult(Participants participants) {
        this.participants = participants;
        this.dealer = participants.getDealer();
        this.players = participants.getPlayers();
    }

    public Map<Result, Long> aggregateDealerStatisticResult() {
        Map<Result, Long> statisticResult = new EnumMap<>(Result.class);
        for (Player player : players) {
            Result result = player.judgeResult(dealer);
            Result replacedResult = result.replaceWinWithLose();
            statisticResult.put(replacedResult,
                statisticResult.getOrDefault(replacedResult, DEFAULT_VALUE) + INCREASE_COUNT);
        }
        return statisticResult;
    }

    public Map<String, Result> aggregateParticipantNameAndResult() {
        Map<String, Result> playerNameAndResult = new HashMap<>();
        for (Player player : players) {
            playerNameAndResult.put(player.getName(), player.judgeResult(dealer));
        }
        return playerNameAndResult;
    }
}
