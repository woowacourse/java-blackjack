package domain.user;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static utils.Assistant.addCards;

import domain.Money;
import java.util.List;
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
    @DisplayName("GetDealerProfit")
    void test_getDealerProfit() {
        addCards(users.players().get(0), "10", "A");
        addCards(users.players().get(2), "10", "5");
        addCards(users.dealer(), "10", "7");

        assertThat(users.getDealerProfit())
                .isEqualTo(new Money((int) (-1_000 * 1.5) + 2_000));
    }
}