package domain.player;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BetAmountTest {
    private static final int MIN_AMOUNT = 100;
    private static final int MAX_AMOUNT = 1_000_000;

    @Test
    @DisplayName("배팅 금액이 최대 금액을 초과하면 예외가 발생한다")
    void betMaxRange() {
        final int value = 1000001;
        Assertions.assertThatThrownBy(() -> new BetAmount(value)).isInstanceOf(IllegalArgumentException.class)
                .hasMessage(String.format("배팅 금액: %d, 배팅 금액은 최대 %d 이하입니다", value, MAX_AMOUNT));
    }

    @Test
    @DisplayName("배팅 금액이 최소 금액을 미만이면 예외가 발생한다")
    void betMinRange() {
        final int value = 99;
        Assertions.assertThatThrownBy(() -> new BetAmount(value)).isInstanceOf(IllegalArgumentException.class)
                .hasMessage(String.format("배팅 금액: %d, 배팅 금액은 최소 %d 이상입니다", value, MIN_AMOUNT));
    }
}
