package blackjack.domain.dto;

import blackjack.domain.Result;
import blackjack.domain.Users;
import blackjack.domain.card.GamePoint;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Name;
import blackjack.domain.user.User;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class GameResultDto {
    private final Map<String, Result> userResult;
    private final Map<Result, Integer> dealerResult = new HashMap<>();

    public GameResultDto(final Dealer dealer, final Users users) {
        userResult = new LinkedHashMap<>();
        judgeUsers(users, dealer.getGamePoint());
    }

    private void judgeUsers(final Users users, final GamePoint gamePoint) {
        enrollUser(users.getUsersGreaterThan(gamePoint), Result.WIN);
        enrollUser(users.getUsersEqualTo(gamePoint), Result.DRAW);
        enrollUser(users.getUsersLowerThan(gamePoint), Result.LOSE);
    }

    private void enrollUser(final List<User> winningUsers, Result result) {
        for (User user : winningUsers) {
            final Name name = user.getName();
            userResult.put(name.getValue(), result);
            addResultToDealer(getDealerResultByUsers(result));
        }
    }

    private Result getDealerResultByUsers(final Result result) {
        if (result == Result.WIN) {
            return Result.LOSE;
        }
        if (result == Result.LOSE) {
            return Result.WIN;
        }
        if (result == Result.DRAW) {
            return Result.DRAW;
        }
        throw new AssertionError();
    }

    private void addResultToDealer(final Result result) {
        dealerResult.computeIfPresent(result, (key, value) -> value + 1);
        dealerResult.putIfAbsent(result, 1);
    }

    public Map<String, Result> getUserResult() {
        return userResult;
    }

    public Map<Result, Integer> getDealerResult() {
        return dealerResult;
    }
}
