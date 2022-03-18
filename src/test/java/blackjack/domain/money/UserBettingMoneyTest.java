
package blackjack.domain.money;

import static blackjack.domain.Fixtures.JACK_DIAMOND;
import static blackjack.domain.Fixtures.KING_DIAMOND;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.player.User;
import blackjack.domain.result.Result;
import blackjack.money.BettingMoney;
import blackjack.money.UserBettingMoney;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class UserBettingMoneyTest {

    private final User user1 = new User("pobi", new ArrayList<>(Arrays.asList(JACK_DIAMOND, KING_DIAMOND)));
    private final User user2 = new User("jun", new ArrayList<>(Arrays.asList(JACK_DIAMOND, KING_DIAMOND)));
    private final User user3 = new User("jason", new ArrayList<>(Arrays.asList(JACK_DIAMOND, KING_DIAMOND)));
    private final Map<String, Result> userResult = new HashMap<>(Map.ofEntries(
            Map.entry(user1.getName(), Result.WIN),
            Map.entry(user2.getName(), Result.DRAW),
            Map.entry(user3.getName(), Result.LOSE)
    ));

    @Test
    @DisplayName("유저들의 베팅머니를 생성한다.")
    void createUserBettingMoney() {
        final Map<User, BettingMoney> expected = new HashMap<>(Map.ofEntries(
                Map.entry(user1, BettingMoney.of(10000)),
                Map.entry(user2, BettingMoney.of(20000)),
                Map.entry(user3, BettingMoney.of(30000))
        ));
        UserBettingMoney userBettingMoney = new UserBettingMoney(List.of(user1, user2, user3));
        userBettingMoney.putBettingMoney(user1, 10000);
        userBettingMoney.putBettingMoney(user2, 20000);
        userBettingMoney.putBettingMoney(user3, 30000);

        final Map<User, BettingMoney> actual = userBettingMoney.getUserBettingMoney();
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("유저들의 수익을 계산한다.")
    void getUserRevenue() {
        final Map<String, Integer> expected = new HashMap<>(Map.ofEntries(
                Map.entry(user1.getName(), 10000),
                Map.entry(user2.getName(), 0),
                Map.entry(user3.getName(), -30000)
        ));
        UserBettingMoney userBettingMoney = new UserBettingMoney(List.of(user1, user2, user3));
        userBettingMoney.putBettingMoney(user1, 10000);
        userBettingMoney.putBettingMoney(user2, 20000);
        userBettingMoney.putBettingMoney(user3, 30000);

        final Map<String, Integer> userRevenue = userBettingMoney.getUserRevenue(userResult);
        assertThat(userRevenue).isEqualTo(expected);
    }

    @Test
    @DisplayName("딜러의 수익을 계산한다.")
    void getDealerRevenue() {
        UserBettingMoney userBettingMoney = new UserBettingMoney(List.of(user1, user2, user3));
        userBettingMoney.putBettingMoney(user1, 10000);
        userBettingMoney.putBettingMoney(user2, 20000);
        userBettingMoney.putBettingMoney(user3, 30000);
        userBettingMoney.getUserRevenue(userResult);
        final int expected = 20000;

        final int actual = userBettingMoney.getDealerRevenue();
        assertThat(actual).isEqualTo(expected);
    }
}
