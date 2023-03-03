package blackjack.domain;

import java.util.*;

public class GameResult {
    private final Map<Name, List<Result>> userResult;
    private final List<Result> dealerResult = new ArrayList<>();

    public GameResult(final Dealer dealer, final Users users) {
        userResult = new LinkedHashMap<>();
        judgeUsers(users, dealer.getGamePoint());
    }

    private void judgeUsers(final Users users, final GamePoint gamePoint) {
        enrollUser(users.getUsersGreaterThan(gamePoint), Result.WIN);
        enrollUser(users.getUsersEqualTo(gamePoint), Result.DRAW);
        enrollUser(users.getUsersLowerThan(gamePoint), Result.LOSE);
    }

    private void enrollUser(final List<User> winningUsers, Result rs) {
        for (User user : winningUsers) {
            final Name name = user.getName();
            userResult.put(name, Arrays.asList(rs));
            addResultToDealer(getDealerResultOf(rs));
        }
    }

    private Result getDealerResultOf(final Result rs) {
        if (rs == Result.WIN) {
            return Result.LOSE;
        }
        if (rs == Result.LOSE) {
            return Result.WIN;
        }
        if ( rs == Result.DRAW){
            return Result.DRAW;
        }
        throw new AssertionError();
    }

    private void addResultToDealer(final Result rs) {
        dealerResult.add(rs);
    }

    public Map<Name, List<Result>> getUserResult() {
        return userResult;
    }

    public List<Result> getDealerResult() {
        return dealerResult;
    }
}
