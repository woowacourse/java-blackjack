package blackjack.domain.money;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.money.BettingMoney;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class BettingMoneyTest {

    @Test
    @DisplayName("배팅 머니 생성을 확인한다.")
    void createBettingMoney() {
        final int expected = 10000;
        BettingMoney bettingMoney = BettingMoney.of(expected);

        final int actual = bettingMoney.getValue();
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @ValueSource(ints = {999, 0, -10000})
    @DisplayName("최소 배팅 머니 충족하지 않을 때 예외를 발생시킨다.")
    void validateBettingMoney(final int money) {
        assertThatThrownBy(() ->
                BettingMoney.of(money))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
