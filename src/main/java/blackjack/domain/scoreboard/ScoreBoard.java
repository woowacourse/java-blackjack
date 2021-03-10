package blackjack.domain.scoreboard;

import blackjack.domain.scoreboard.result.GameResult;
import blackjack.domain.scoreboard.result.UserGameResult;
import blackjack.domain.user.User;

import java.util.Collections;
import java.util.Map;
import java.util.Objects;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class ScoreBoard {
    private final Map<User, UserGameResult> userResults;
    private final GameResult dealerGameResult;

    public ScoreBoard(Map<User, UserGameResult> userResults, GameResult dealerGameResult) {
        this.userResults = userResults;
        this.dealerGameResult = dealerGameResult;
    }

    public Map<WinOrLose, Long> dealerWinOrLoseCounts() {
        return userResults.keySet().stream()
                .map(userResults::get)
                .map(UserGameResult::getWinOrLose)
                .collect(groupingBy(WinOrLose::opposite, counting()));
    }

    public long calculateDealerIncome() {
        return userResults.keySet().stream()
                .map(userResults::get)
                .mapToLong(UserGameResult::getIncome)
                .map(this::toNegative)
                .sum();
    }

    private long toNegative(long income) {
        return -1 * income;
    }

    public Map<User, UserGameResult> getUserResults() {
        return Collections.unmodifiableMap(userResults);
    }

    public GameResult getDealerGameResult() {
        return dealerGameResult;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ScoreBoard)) return false;
        ScoreBoard that = (ScoreBoard) o;
        return Objects.equals(getUserResults(), that.getUserResults()) && Objects.equals(dealerGameResult, that.dealerGameResult);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserResults(), dealerGameResult);
    }
}
