package domain;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static utils.Assistant.addCards;

import domain.user.Dealer;
import domain.user.Player;
import domain.user.Users;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UsersTest {
    private Users users;

    @BeforeEach
    void setUp() {
        List<Player> players = List.of(new Player("조이", new Money(1_000)),
                new Player("포비", new Money(2_000)));

        users = new Users(players, new Dealer());
    }

    @Test
    @DisplayName("딜러 최종 수익을 계산한다.")
    void test_getDealerProfit() {
        addCards(users.players().get(0), "10", "A");
        addCards(users.players().get(1), "10", "5");
        addCards(users.dealer(), "10", "7");

        Map<String, Money> profits = users.getPlayersProfits(users);

        assertThat(users.getDealerProfit(profits))
                .isEqualTo(new Money((int) (-1_000 * 1.5) + 2_000));
    }
}