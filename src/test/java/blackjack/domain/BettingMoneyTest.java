package blackjack.domain;

import static blackjack.domain.BettingMoney.NOT_ALLOW_BLANK_MESSAGE;
import static blackjack.domain.BettingMoney.NOT_ALLOW_NULL_MESSAGE;
import static blackjack.domain.BettingMoney.NOT_ALLOW_NUMBER_MESSAGE;
import static blackjack.domain.BettingMoney.NOT_ALLOW_UNIT_OF_THOUSAND_MESSAGE;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class BettingMoneyTest {

    @Test
    @DisplayName("배팅 금액은 null 이 될 수 없다")
    void not_allow_null() {
        assertThatThrownBy(() -> new BettingMoney("foo", null))
                .isInstanceOf(NullPointerException.class)
                .hasMessage(NOT_ALLOW_NULL_MESSAGE);
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " "})
    @DisplayName("배팅 금액에 공백은 허용하지 않는다")
    void not_allow_space(String inputMoney) {
        assertThatThrownBy(() -> new BettingMoney("foo", inputMoney))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(NOT_ALLOW_BLANK_MESSAGE);
    }

    @ParameterizedTest
    @ValueSource(strings = {"a", "#"})
    @DisplayName("배팅 금액은 숫자여야 한다")
    void not_allow_no_number(String inputMoney) {
        assertThatThrownBy(() -> new BettingMoney("foo", inputMoney))
                .isInstanceOf(NumberFormatException.class)
                .hasMessage(NOT_ALLOW_NUMBER_MESSAGE);
    }

    @ParameterizedTest
    @ValueSource(strings = {"0", "900", "1800"})
    @DisplayName("1000원 이상이면서 1000원 단위의 숫자를 입력해야 한다")
    void should_unit_of_1000(String inputMoney) {
        assertThatThrownBy(() -> new BettingMoney("foo", inputMoney))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(NOT_ALLOW_UNIT_OF_THOUSAND_MESSAGE);
    }
}
