package domain;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class BettingMoneyTest {
    @ParameterizedTest
    @ValueSource(ints = {999, 1001})
    void invalidNumber(final int invalidNumber) {
        assertThatIllegalArgumentException().isThrownBy(() -> new BettingMoney(invalidNumber))
                .withMessage("1000 단위만 가능합니다.");
    }

    @ParameterizedTest
    @ValueSource(ints = {1000, 2000})
    void validNumber(final int validNumber) {
        assertThatCode(() -> new BettingMoney(validNumber))
                .doesNotThrowAnyException();
    }
}
