package domain.result;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingInt;

public class DealerResult extends BlackJackResult<Map<WinLose, Integer>> {
    private final Map<WinLose, Integer> winLose;

    public DealerResult(List<UserResult> userResults) {
        super("딜러");
        this.winLose = mappingResults(userResults);
    }

    private Map<WinLose, Integer> mappingResults(List<UserResult> userResults) {
        return userResults.stream()
                .collect(groupingBy(UserResult::getWinLose, summingInt(x -> 1)));
    }

    @Override
    public Map<WinLose, Integer> getWinLose() {
        return winLose;
    }
}
