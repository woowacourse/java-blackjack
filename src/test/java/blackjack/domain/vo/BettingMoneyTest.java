package blackjack.domain.vo;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class BettingMoneyTest {

    @Test
    @DisplayName("배팅 금액이 1000단위인지 검증한다.")
    void createBettingMoney() {
        assertThatCode(() -> new BettingMoney(1000))
            .doesNotThrowAnyException();
    }

    @ParameterizedTest
    @ValueSource(ints = {999, 1001, 1, 100})
    @DisplayName("배팅 금액이 1000단위가 아닌 경우 예외를 발생한다.")
    void throwExceptionBettingMoneyDivide1000(int value) {
        assertThatIllegalArgumentException()
            .isThrownBy(() -> new BettingMoney(value))
            .withMessage("배팅 금액은 1000단위어야 합니다.");
    }
}
