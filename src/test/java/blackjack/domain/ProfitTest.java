package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class ProfitTest {

    @DisplayName("Profit 정상적으로 생성된다.")
    @Test
    void createBet() {
        // given
        int input = 1000;
        Profit profit = Profit.of(input);

        // when & then
        assertThat(profit.getProfit()).isEqualTo(input);
    }

    @ParameterizedTest(name = "양수가 아닐 경우 예외가 발생한다.")
    @ValueSource(ints = {-1000, 0})
    void validatePositiveNumber(int input) {
        // when & then
        assertThatThrownBy(() -> Profit.of(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("베팅 금액은 1 이상이여야 합니다.");
    }

    @ParameterizedTest(name = "100,000,000을 초과할 경우 예외가 발생한다.")
    @ValueSource(ints = {100_000_001})
    void validateMaxNumber(int input) {
        // when & then
        assertThatThrownBy(() -> Profit.of(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("베팅 금액은 100,000,000 이하여야 합니다.");
    }

    @DisplayName("결과에 따라 수익이 정상적으로 생성된다.")
    @Test
    void calculateProfit() {
        // given
        int money = 1000;
        Profit profit = Profit.of(money);

        // when
        int finalProfit1 = profit.calculateProfit(Result.WIN).getProfit();
        int finalProfit2 = profit.calculateProfit(Result.BLACKJACK).getProfit();
        int finalProfit3 = profit.calculateProfit(Result.LOSE).getProfit();
        int finalProfit4 = profit.calculateProfit(Result.PUSH).getProfit();

        // then
        assertThat(finalProfit1).isEqualTo(1000);
        assertThat(finalProfit2).isEqualTo(1500);
        assertThat(finalProfit3).isEqualTo(-1000);
        assertThat(finalProfit4).isEqualTo(1000);
    }
}
