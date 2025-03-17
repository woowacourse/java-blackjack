package blackjack.model.betting;

import static org.assertj.core.api.Assertions.assertThatCode;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("배팅 금액 테스트")
class BetAmountTest {
    @DisplayName("금액은 1000원 단위이고, 1,000원 ~ 1,000,000원이다.")
    @ParameterizedTest
    @ValueSource(ints = {1000, 5000, 1000000})
    void createBetAmountTest(int money) {
        // when, then
        assertThatCode(() -> new BetAmount(money))
                .doesNotThrowAnyException();
    }

    @DisplayName("금액은 1,000원 미만이면 예외를 발생시킨다.")
    @Test
    void createBetAmountTest_ExceptionWhenUnder1000() {
        // given
        int money = 900;

        // when, then
        assertThatCode(() -> new BetAmount(money))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("배팅 금액은 1000원 단위여야하고, 1,000원 ~ 1,000,000원까지 가능합니다.");
    }

    @DisplayName("금액은 1,000,000원 초과이면 예외를 발생시킨다.")
    @Test
    void createBetAmountTest_ExceptionWhenOver1000000() {
        // given
        int money = 1_000_001;

        // when, then
        assertThatCode(() -> new BetAmount(money))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("배팅 금액은 1000원 단위여야하고, 1,000원 ~ 1,000,000원까지 가능합니다.");
    }

    @DisplayName("금액이 1000원 단위가 아니이면 예외를 발생시킨다.")
    @Test
    void createBetAmountTest_ExceptionWhenUnitNot1000() {
        // given
        int money = 1300;

        // when, then
        assertThatCode(() -> new BetAmount(money))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("배팅 금액은 1000원 단위여야하고, 1,000원 ~ 1,000,000원까지 가능합니다.");
    }
}