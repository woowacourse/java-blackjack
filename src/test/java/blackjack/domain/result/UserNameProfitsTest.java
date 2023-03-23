package blackjack.domain.result;

import static java.util.Map.entry;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.money.Money;
import blackjack.domain.user.DealerName;
import blackjack.domain.user.Name;
import blackjack.domain.user.PlayerName;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UserNameProfitsTest {

    @Test
    @DisplayName("플레이어의 이름과 수익금을 넣어주면, 딜러의 수익금까지 계산해 반환한다.")
    void addPlayerProfitsTest() {
        final PlayerName testPlayerName = new PlayerName("홍실");
        final Money profit = new Money(1_000);
        final Map<PlayerName, Money> playerNameProfits = Map.of(testPlayerName, profit);

        final UserNameProfits userNameProfits = UserNameProfits.addDealerProfit(playerNameProfits);
        final Map<Name, Money> userNameProfitMapper = userNameProfits.getUserNameProfitMapper();

        assertThat(userNameProfitMapper)
                .containsExactly(entry(new DealerName(), profit.opposite()),
                        entry(testPlayerName, profit));
    }
}
