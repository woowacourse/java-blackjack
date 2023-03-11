package blackjack.domain.money;

import static java.util.Map.entry;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.user.Name;
import org.assertj.core.api.InstanceOfAssertFactories;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DepositTest {

    private static final Name TEST_PLAYER_NAME1 = new Name("필립");

    @Test
    @DisplayName("플레이어가 돈을 배팅하는 기능 테스트")
    void battingTest() {
        final Deposit deposit = new Deposit();

        deposit.betting(TEST_PLAYER_NAME1, new Money(1000));

        assertThat(deposit)
                .extracting("deposit", InstanceOfAssertFactories.map(Name.class, Money.class))
                .contains(entry(TEST_PLAYER_NAME1, new Money(1000)));
    }

    @Test
    @DisplayName("플레이어 이름과 수익률을 입력하면 수익금을 반환하는 기능 테스트")
    void getProfitTest() {
        final Deposit deposit = new Deposit();

        deposit.betting(TEST_PLAYER_NAME1, new Money(1000));
        final Money profit = deposit.getProfit(TEST_PLAYER_NAME1, 1.5);

        assertThat(profit.getValue()).isEqualTo(1500);
    }
}
