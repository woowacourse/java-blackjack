package blackjack.domain;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ResultBoard {
    private final Map<User, Result> resultBoard;

    public ResultBoard() {
        this.resultBoard = new HashMap<>();
    }

    public void putResultByUser(Dealer dealer, Users users) {
        users.users()
                .forEach(user -> {
                    resultBoard.put(user, Result.decide(dealer, user));
                });
    }

    public Map<User, Result> resultBoard() {
        return Collections.unmodifiableMap(resultBoard);
    }
}
