package blackjack.domain.dto;

import blackjack.domain.ResultType;
import blackjack.domain.user.User;

import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class Results {
    private final Map<User, ResultType> results;

    public Results(Map<User, ResultType> results) {
        this.results = Collections.unmodifiableMap(results);
    }

    public ResultType getResultOf(User user) {
        return results.get(user);
    }

    public Set<User> userSet() {
        return results.keySet();
    }

    public int count(ResultType resultType) {
        return (int) this.results.values().stream().parallel()
                .filter(resultType::equals)
                .count();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Results results1 = (Results) o;
        return Objects.equals(results, results1.results);
    }

    @Override
    public int hashCode() {
        return Objects.hash(results);
    }
}
