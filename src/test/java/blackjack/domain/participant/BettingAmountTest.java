package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class BettingAmountTest {

    @DisplayName("베팅 금액은 양수여야 합니다.")
    @ParameterizedTest(name = "[{index}] 금액 : {0}")
    @ValueSource(ints = {-2, -1, 0})
    void bettingAmountNotPositiveExceptionTest(final int amount) {
        assertThatThrownBy(() -> new BettingAmount(amount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("베팅 금액은 양수여야 합니다.");
    }

    @DisplayName("베팅 금액은 양수여야 합니다.")
    @ParameterizedTest(name = "[{index}] 금액 : {0}")
    @ValueSource(ints = {8, 9})
    void bettingAmountNotDivisibleExceptionTest(final int amount) {
        assertThatThrownBy(() -> new BettingAmount(amount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("베팅 금액은 10원 단위여야 합니다.");
    }

    @DisplayName("베팅 금액은 생성 당시의 값을 지니고 있어야 합니다.")
    @ParameterizedTest(name = "[{index}] 금액 : {0}")
    @ValueSource(ints = {10, 100, 10000})
    void createBettingAmountTest(final int expectedAmount) {
        final BettingAmount bettingAmount = new BettingAmount(expectedAmount);

        final int actualAmount = bettingAmount.getAmount();
        assertThat(actualAmount).isEqualTo(expectedAmount);
    }

}
