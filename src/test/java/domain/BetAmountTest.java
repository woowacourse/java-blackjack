package domain;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class BetAmountTest {
    @ParameterizedTest(name = "배팅 금액은 최소 100원, 최대 1억이다.")
    @ValueSource(ints = {99, 100000001})
    void createBetAmountFailTest(int money) {
        assertThatThrownBy(() -> BetAmount.from(money))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest(name = "배팅 금액은 최소 100원, 최대 1억이다.")
    @ValueSource(ints = {100, 100000000})
    void createBetAmountSuccessTest(int money) {
        assertDoesNotThrow(() -> BetAmount.from(money));
    }

    @ParameterizedTest(name = "배율에 맞게 배팅금액을 돌려받을 수 있다.")
    @ValueSource(ints = {100, 2000, 30000, 100000000},
            doubles = {0, 1.5, 2.5})
    void receiveBetAmountSuccessTest(int money, double ratio) {
        BetAmount betAmount = BetAmount.from(money);

        assertThat(betAmount.applyRatio(ratio).getMoney())
                .isEqualTo((int) (money * ratio));
    }
}
