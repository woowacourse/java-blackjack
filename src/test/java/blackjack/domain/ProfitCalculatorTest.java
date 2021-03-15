package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;
import blackjack.domain.user.User;
import java.util.LinkedHashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ProfitCalculatorTest {

    @DisplayName("플레이어와 플레이어의 승패 여부가 담긴 자료를 이용해 참가자들의 수익을 계산한다.")
    @Test
    void profit() {
        Dealer dealer = new Dealer();
        Player player1 = new Player("choonsik", 1000);
        Player player2 = new Player("pobi", 5000);
        Map<Player, Status> result = new LinkedHashMap<Player, Status>() {{
            put(player1, Status.BLACKJACK);
            put(player2, Status.LOSE);
        }};

        Map<User, Money> profit = new LinkedHashMap<User, Money>() {{
            put(dealer, new Money(3500));
            put(player1, new Money(1500));
            put(player2, new Money(-5000));
        }};

        ProfitCalculator profitCalculator = new ProfitCalculator();
        assertThat(profitCalculator.calculateProfit(dealer, result))
            .isEqualTo(profit);
    }

}