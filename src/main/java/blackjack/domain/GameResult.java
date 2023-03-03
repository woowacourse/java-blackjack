package blackjack.domain;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class GameResult {
    private final Map<Name, List<Result>> result;
    private final Dealer dealer;

    public GameResult(final Dealer dealer, final Users users) {
        result = new LinkedHashMap<>();
        this.dealer = dealer;
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
            result.put(name, Arrays.asList(rs));
            addResultToDealer(dealer, getDealerResultOf(rs));
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

    private void addResultToDealer(final Dealer dealer, final Result rs) {
        final Name dealerName = dealer.getName();
        if (!result.containsKey(dealerName)) {
            result.put(dealerName, Arrays.asList(rs));
        }
        result.get(dealer).add(rs);
    }

}
