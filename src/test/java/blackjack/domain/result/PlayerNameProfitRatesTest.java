package blackjack.domain.result;

import static java.util.Map.entry;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.money.BettingMoney;
import blackjack.domain.money.Money;
import blackjack.domain.user.DealerName;
import blackjack.domain.user.Name;
import blackjack.domain.user.PlayerName;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerNameProfitRatesTest {

    @Test
    @DisplayName("플레이어의 이름과 배팅금을 입력받으면, 각 플레이어와 딜러의 수익금을 반환한다.")
    void calculateProfitsTest() {
        final PlayerName testPlayerName = new PlayerName("홍실");
        final Map<PlayerName, Double> playerNameAndProfitRates = Map.of(testPlayerName, 1.5);
        final Map<PlayerName, BettingMoney> playerNameAndBettingMoney = Map.of(testPlayerName,
                new BettingMoney(2000));
        final PlayerNameProfitRates playerNameProfitRates = new PlayerNameProfitRates(playerNameAndProfitRates);

        final UserNameProfits userNameProfits = playerNameProfitRates.calculateProfits(playerNameAndBettingMoney);
        final Map<Name, Money> userNameProfitMapper = userNameProfits.getUserNameProfitMapper();

        assertThat(userNameProfitMapper)
                .containsExactly(entry(new DealerName(), new Money(-3_000)),
                        entry(testPlayerName, new Money(3_000)));
    }
}
