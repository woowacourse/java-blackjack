package blackjack.domain.participants;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class BettingTest {

    @ParameterizedTest
    @DisplayName("배팅 금액이 int 범위 밖이거나 숫자가 아닌 경우 예외 처리")
    @ValueSource(strings = {"a", "2147483648"})
    void invalidBetting(String input) {
        assertThatIllegalArgumentException().isThrownBy(() ->
            Betting.valueOf(input))
            .withMessage("존재할 수 없는 배팅 금액입니다.");
    }

    @Test
    @DisplayName("배팅 금액이 0이하일 경우 예외처리")
    void nonPositiveBetting() {
        assertThatIllegalArgumentException().isThrownBy(() ->
            Betting.valueOf("0"))
            .withMessage("배팅 금액은 양수입니다.");
    }
}
