package domain.result;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingInt;

public class DealerResult extends BlackJackResult<Map<WinLose, Integer>> {
    private final Map<WinLose, Integer> winLose;

    public DealerResult(List<PlayerResult> playerResults) {
        super("딜러");
        this.winLose = mappingResults(playerResults);
    }

    private Map<WinLose, Integer> mappingResults(List<PlayerResult> playerResults) {
        return playerResults.stream()
                .collect(groupingBy(result -> WinLose.reverse(result.getWinLose()), summingInt(x -> 1)));
    }

    @Override
    public Map<WinLose, Integer> getWinLose() {
        return winLose;
    }

    @Override
    public String toString() {
        return getGamerName() + ": " + convertNullToZero(winLose.get(WinLose.WIN)) + "WIN " + convertNullToZero(winLose.get(WinLose.LOSE)) + "LOSE";
    }

    private String convertNullToZero(Integer input) {
        if (input == null) {
            return "0";
        }
        return Integer.toString(input);
    }
}
