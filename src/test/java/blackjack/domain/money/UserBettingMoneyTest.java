
package blackjack.domain.money;

import static blackjack.domain.Fixtures.JACK_DIAMOND;
import static blackjack.domain.Fixtures.KING_DIAMOND;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

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

    private User user1 = new User("pobi", new ArrayList<>(Arrays.asList(JACK_DIAMOND, KING_DIAMOND)));
    private User user2 = new User("jun", new ArrayList<>(Arrays.asList(JACK_DIAMOND, KING_DIAMOND)));
    private User user3 = new User("jason", new ArrayList<>(Arrays.asList(JACK_DIAMOND, KING_DIAMOND)));
    private Map<User, Result> userResult = new HashMap<>(Map.ofEntries(
            Map.entry(user1, Result.WIN),
            Map.entry(user2, Result.DRAW),
            Map.entry(user3, Result.LOSE)
    ));

    @Test
    @DisplayName("유저들의 베팅머니를 생성한다.")
    void createUserBettingMoney() {
        assertThatCode(() -> {
            new UserBettingMoney(List.of(user1, user2, user3));
        }).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("유저들의 수익을 계산한다.")
    void getUserRevenue() {
        final Map<User, Integer> expected = new HashMap<>(Map.ofEntries(
                Map.entry(user1, 10000),
                Map.entry(user2, 0),
                Map.entry(user3, -30000)
        ));
        UserBettingMoney userBettingMoney = new UserBettingMoney(List.of(user1, user2, user3));
        userBettingMoney.putBettingMoney(user1, 10000);
        userBettingMoney.putBettingMoney(user2, 20000);
        userBettingMoney.putBettingMoney(user3, 30000);

        final Map<User, Integer> userRevenue = userBettingMoney.getUserRevenue(userResult);
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
