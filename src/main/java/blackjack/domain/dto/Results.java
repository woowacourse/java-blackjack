package blackjack.domain.dto;

import blackjack.domain.ResultType;
import blackjack.domain.user.User;

import java.util.*;

public class Results {
    private final Map<User, ResultType> results;

    public Results(Map<User, ResultType> results) {
        this.results = Collections.unmodifiableMap(results);
    }

    public ResultType getResultOf(User user) {
        return results.get(user);
    }

    public Set<User> keySet() {
        return results.keySet();
    }

    public Collection<ResultType> values() {
        return this.results.values();
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
