package domain;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BetAmountTest {

    @ParameterizedTest
    @ValueSource(strings = {"3.14", "돈", ""})
    void 베팅_금액에_정수가_아닌_값을_입력하면_에러가_발생한다(String input) {
        // given
        // when,then
        assertThatThrownBy(() -> new BetAmount(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("베팅 금액은 숫자로 입력해야 합니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"-1", "0"})
    void 베팅_금액에_0이하인_값을_입력하면_에러가_발생한다(String input) {
        // given
        // when,then
        assertThatThrownBy(() -> new BetAmount(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("베팅 금액은 양의 정수여야 합니다.");
    }
}