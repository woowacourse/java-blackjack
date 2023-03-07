package domain;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class BetAmountTest {
    @ParameterizedTest(name = "배팅 금액은 최소 10원, 최대 1억이다.")
    @ValueSource(ints = {9, 100000001})
    void createBetAmountFailTest(int money) {
        assertThatThrownBy(() -> BetAmount.from(money))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest(name = "배팅 금액은 최소 10원, 최대 1억이다.")
    @ValueSource(ints = {10, 100000000})
    void createBetAmountSuccessTest(int money) {
        assertDoesNotThrow(() -> BetAmount.from(money));
    }

    @ParameterizedTest(name = "배팅 금액의 2배를 돌려받을 수 있다.")
    @ValueSource(ints = {100, 2000, 30000, 100000000})
    void receiveBetAmountSuccessTest(int money) {
        BetAmount betAmount = BetAmount.from(money);

        assertThat(betAmount.receiveDouble().getMoney())
                .isEqualTo(money * 2);
    }

    @ParameterizedTest(name = "배팅 금액의 1.5배를 추가로(2.5배) 돌려받을 수 있다.")
    @ValueSource(ints = {100, 2000, 30000, 100000000})
    void receiveWithBlackjackSuccessTest(int money) {
        BetAmount betAmount = BetAmount.from(money);

        assertThat(betAmount.receiveWithBlackjack().getMoney())
                .isEqualTo(money * 2.5);
    }
}
