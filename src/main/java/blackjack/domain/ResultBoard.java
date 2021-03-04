package blackjack.domain;

import java.util.*;
import java.util.stream.Collectors;

public class ResultBoard {
    private final Map<User, Result> resultBoard;

    public ResultBoard(Dealer dealer, Users users) {
        this.resultBoard = new HashMap<>();
        putResultByUser(dealer, users);
    }

    private void putResultByUser(Dealer dealer, Users users) {
        users.users()
                .forEach(user -> {
                    this.resultBoard.put(user, Result.decide(dealer, user));
                });
    }

    public Map<Result, Integer> dealerResultBoard() {
        return Result.countByResults(groupResult());
    }

    private List<Result> groupResult() {
        return resultBoard.values().stream()
                .map(value -> value.reverse(value))
                .collect(Collectors.toList());
    }

    public Map<User, Result> userResultBoard() {
        return Collections.unmodifiableMap(this.resultBoard);
    }
}
