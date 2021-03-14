package blackjack.domain.scoreboard;

import blackjack.domain.user.Name;
import blackjack.domain.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class GameResultTest {
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
        GameResult gameResult = new GameResult(temp);
        Set<User> userSet = new HashSet<>(Arrays.asList(first, second, third, fourth));

        assertThat(gameResult.getUserSet()).isEqualTo(userSet);
    }

    @Test
    void testGetUserProfit() {
        GameResult gameResult = new GameResult(temp);
        List<Double> userProfits = new ArrayList<>();
        userProfits.add(-1D);
        userProfits.add(-1D);
        userProfits.add(1D);
        userProfits.add(0D);

        assertThat(gameResult.getUserProfit().collect(Collectors.toList())).isEqualTo(userProfits);
    }

    @DisplayName("딜러 수익률 테스트")
    @Test
    void testCalculateDealerProfit() {
        GameResult gameResult = new GameResult(temp);

        assertThat(gameResult.calculateDealerProfit()).isEqualTo(1);
    }
}
