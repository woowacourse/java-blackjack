package blackjack.domain.betting;

import blackjack.exception.ErrorMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BettingAmountTest {

    @ParameterizedTest
    @ValueSource(ints = {1000, 1001, 5000, 49999, 50000})
    @DisplayName("배팅 금액이 1000원 이상 50000원 이하이면 성공한다")
    void createBettingAmountTest(int amount) {
        // when
        BettingAmount bettingAmount = new BettingAmount(amount);

        // then
        assertThat(bettingAmount.getAmount()).isEqualTo(amount);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 999, 50001, 100000})
    @DisplayName("배팅 금액이 1000원 이상 50000원 이하가 아니면 예외를 발생한다")
    void bettingAmountRangeErrorTest(int amount) {
        // when & then
        assertThatThrownBy(() -> new BettingAmount(amount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.OUT_OF_AMOUNT_RANGE.getMessage());
    }
}
