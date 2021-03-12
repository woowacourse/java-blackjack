package blackjack.domain.scoreboard;

import blackjack.domain.user.Name;
import blackjack.domain.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class UserGameResultTest {
    private Map<User, Double> temp;
    private User first;
    private User second;
    private User third;
    private User fourth;

    @BeforeEach
    void setUp() {
        first = new User(Name.from("욘"));
        second = new User(Name.from("웨지"));
        third = new User(Name.from("포비"));
        fourth = new User(Name.from("제이슨"));

        double firstUserGameResult = Profit.DRAW.getProfit();
        double secondUserGameResult = Profit.WIN.getProfit();
        double thirdUserGameResult = Profit.LOSE.getProfit();
        double fourthUserGameResult = Profit.LOSE.getProfit();

        temp = new HashMap<>();
        temp.put(first, firstUserGameResult);
        temp.put(second, secondUserGameResult);
        temp.put(third, thirdUserGameResult);
        temp.put(fourth, fourthUserGameResult);
    }

    @DisplayName("유저 게임 결과 생성 테스트")
    @Test
    void testUserGameResult() {
        UserGameResult userGameResult = new UserGameResult(temp);
        Set<User> userSet = new HashSet<>(Arrays.asList(first, second, third, fourth));

        assertThat(userGameResult.getUserSet()).isEqualTo(userSet);
    }

    @Test
    void testGetUserProfit() {
        UserGameResult userGameResult = new UserGameResult(temp);
        Set<Double> userProfits = new HashSet<>();
        userProfits.add(-1D);
        userProfits.add(0D);
        userProfits.add(1D);

        assertThat(userGameResult.getUserProfit().collect(Collectors.toSet())).isEqualTo(userProfits);
    }
}
