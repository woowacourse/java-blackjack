package blackjack.domain.money;

import static java.util.Map.entry;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.user.Name;
import blackjack.domain.user.PlayerName;
import org.assertj.core.api.InstanceOfAssertFactories;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DepositTest {

    private static final PlayerName TEST_PLAYER_NAME1 = new PlayerName("필립");

    @Test
    @DisplayName("플레이어가 돈을 배팅하는 기능 테스트")
    void battingTest() {
        final Deposit deposit = new Deposit();

        deposit.bet(TEST_PLAYER_NAME1, new BettingMoney(1000));

        assertThat(deposit)
                .extracting("deposit", InstanceOfAssertFactories.map(Name.class, BettingMoney.class))
                .contains(entry(TEST_PLAYER_NAME1, new BettingMoney(1000)));
    }
}
