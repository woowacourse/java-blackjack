package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.exception.BettingAmountFormatException;
import blackjack.exception.BettingAmountNonPositiveException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class PotTest {
    @DisplayName("베팅 금액으로 정수가 아닌 값이 입력되면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"", "!", "3만원", "0.0001"})
    void invalidPotAmountNumberFormat(String amount) {
        assertThatThrownBy(() -> new Pot(amount))
                .isInstanceOf(BettingAmountFormatException.class);
    }

    @DisplayName("베팅 금액으로 양의 정수가 아닌 값이 입력되면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"0", "-1000", "-1"})
    void invalidPotAmountNonPositive(String amount) {
        assertThatThrownBy(() -> new Pot(amount))
                .isInstanceOf(BettingAmountNonPositiveException.class);
    }
}
