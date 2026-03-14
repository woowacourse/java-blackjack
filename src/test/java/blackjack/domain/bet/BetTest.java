package blackjack.domain.bet;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class BetTest {

    @DisplayName("배팅 금액이 음수이거나 0이면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(ints = {0, -1, -1000})
    void create_ThrowsException_WhenAmountIsZeroOrLess(int invalidAmount) {
        assertThatThrownBy(() -> new Bet(invalidAmount))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("배팅 금액이 최대 한도를 초과하면 예외가 발생한다.")
    void create_ThrowsException_WhenAmountExceedsLimit() {
        assertThatThrownBy(() -> new Bet(1431665765))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("배팅 금액은 1,431,665,764 이하여야 합니다");
    }

    @DisplayName("정상적인 배팅 금액과 경계값으로 배팅을 생성할 수 있다.")
    @ParameterizedTest
    @ValueSource(ints = {1, 1000, 1431665764})
    void create_Success_WithValidAmounts(int validAmount) {
        Bet bet = new Bet(validAmount);

        assertThat(bet).isNotNull();
    }

    @Test
    @DisplayName("수익률을 전달받아 정확한 수익금을 계산하여 반환한다.")
    void calculateProfit() {
        Bet bet = new Bet(10000);

        assertThat(bet.calculateProfit(ProfitRate.WIN)).isEqualTo(10000);
        assertThat(bet.calculateProfit(ProfitRate.LOSE)).isEqualTo(-10000);
        assertThat(bet.calculateProfit(ProfitRate.DRAW)).isZero();
    }
}
