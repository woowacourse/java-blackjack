package blackjack.model;

import blackjack.model.participant.BetAmount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class BetAmountTest {

    @ParameterizedTest
    @DisplayName("베팅 금액이 양의 정수가 아니면 예외처리한다.")
    @ValueSource(ints = {0, -10, -9999999})
    void validate_not_positive_amount(int input) {
        assertThatThrownBy(() -> new BetAmount(input)).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("유효한 금액이 아닙니다. 베팅 금액은 양의 정수여야 합니다.");
    }

    @ParameterizedTest
    @DisplayName("베팅 금액이 만원 단위가 아니면 예외처리한다.")
    @ValueSource(ints = {1000, 123456778})
    void validate_not_satisfy_min_unit(int input) {
        assertThatThrownBy(() -> new BetAmount(input)).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("베팅은 만원 단위로 가능합니다.");
    }

}
