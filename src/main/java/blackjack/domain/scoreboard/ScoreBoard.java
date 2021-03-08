package blackjack.domain.scoreboard;

import blackjack.domain.user.User;

import java.util.Collections;
import java.util.Map;
import java.util.Objects;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class ScoreBoard {
    private static final long DEFAULT_COUNT = 0L;

    private final Map<User, UserGameResult> userResults;
    private final GameResult gameResult;

    public ScoreBoard(Map<User, UserGameResult> userResults, GameResult gameResult) {
        this.userResults = userResults;
        this.gameResult = gameResult;
    }

    public Map<User, UserGameResult> getUserResults() {
        return Collections.unmodifiableMap(userResults);
    }

    private Map<WinOrLose, Long> makeDealerWinOrLoseMap() {
        return userResults.keySet().stream()
                .map(userResults::get)
                .map(UserGameResult::getWinOrLose)
                .collect(groupingBy(WinOrLose::opposite, counting()));
    }

    public Long countDealerWinOrLose(WinOrLose winOrLose) {
        return makeDealerWinOrLoseMap().getOrDefault(winOrLose, DEFAULT_COUNT);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ScoreBoard that = (ScoreBoard) o;
        return Objects.equals(userResults, that.userResults) && Objects.equals(gameResult, that.gameResult);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userResults, gameResult);
    }
}
