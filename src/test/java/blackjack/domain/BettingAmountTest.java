package blackjack.domain;

import static blackjack.domain.BettingMoney.NOT_ALLOW_UNIT_OF_THOUSAND_MESSAGE;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class BettingAmountTest {

    @ParameterizedTest
    @ValueSource(strings = {"0", "900", "1800"})
    @DisplayName("1000원 이상이면서 1000원 단위의 숫자를 입력해야 한다")
    void should_unit_of_1000(int inputMoney) {
        assertThatThrownBy(() -> new BettingAmount(inputMoney))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(NOT_ALLOW_UNIT_OF_THOUSAND_MESSAGE);
    }
}
