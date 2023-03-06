package blackjack.domain.dto;

import blackjack.domain.BlackJack;
import blackjack.domain.Result;
import blackjack.domain.Users;
import blackjack.domain.card.GamePoint;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Name;
import blackjack.domain.user.User;

import java.util.*;

public class GameResultDto {
    private final Map<String, ResultDto> userResult;
    private final Map<ResultDto, Integer> dealerResult = new HashMap<>();

    public GameResultDto(BlackJack blackJack) {
        userResult = new LinkedHashMap<>();
        judgeUsers(blackJack.getUsers(), blackJack.getDealer().getGamePoint());
    }

    private void judgeUsers(final Users users, final GamePoint gamePoint) {
        enrollUser(users.getUsersGreaterThan(gamePoint), Result.WIN);
        enrollUser(users.getUsersEqualTo(gamePoint), Result.DRAW);
        enrollUser(users.getUsersLowerThan(gamePoint), Result.LOSE);
    }

    private void enrollUser(final List<User> winningUsers, Result result) {
        for (User user : winningUsers) {
            final Name name = user.getName();
            userResult.put(name.getValue(), new ResultDto(result));
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
        dealerResult.computeIfPresent(new ResultDto(result), (key, value) -> value + 1);
        dealerResult.putIfAbsent(new ResultDto(result), 1);
    }

    public Map<String, ResultDto> getUserResult() {
        return userResult;
    }

    public Map<ResultDto, Integer> getDealerResult() {
        return dealerResult;
    }
}
