package blackjack.domain.scoreboard;

import blackjack.domain.user.User;

import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Stream;

public class UserGameResult {
    private final Map<User, WinOrLose> userResults;

    public UserGameResult(Map<User, WinOrLose> userResults) {
        this.userResults = userResults;
    }

    public Stream<WinOrLose> getUserWinOrLose() {
        return userResults.values().stream();
    }

    public Set<Map.Entry<User, WinOrLose>> getResultEntrySet() {
        return userResults.entrySet();
    }

    public Set<User> getUserSet() {
        return userResults.keySet();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserGameResult that = (UserGameResult) o;
        return Objects.equals(userResults, that.userResults);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userResults);
    }
}
