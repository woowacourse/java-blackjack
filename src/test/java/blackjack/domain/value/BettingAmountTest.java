package blackjack.domain.value;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.exception.ExceptionMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class BettingAmountTest {

    @ParameterizedTest
    @DisplayName("음수가 입력될 경우를 검증할 수 있다.")
    @ValueSource(ints = {-10, -1})
    void validateInvalidAmount(int amount) {
        assertThatThrownBy(() -> new BettingAmount(amount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ExceptionMessage.INVALID_BETTING_AMOUNT.getContent());
    }

    @Test
    @DisplayName("배팅금액에 배율을 곱한 것을 구할 수 있다.")
    void calculateMultiplication() {
        BettingAmount bettingAmount = new BettingAmount(1000);

        int actualAmount = bettingAmount.calculateMultiplication(1.5);

        assertThat(actualAmount).isEqualTo(1500);
    }
}