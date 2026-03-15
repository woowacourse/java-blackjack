package domain.betting;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class BettingAmountParserTest {
    @ParameterizedTest
    @ValueSource(strings = {"나무"})
    void 배팅금액_입력이_숫자구성이_아니면_예외처리(String input) {
        assertThatThrownBy(() -> BettingAmountParser.parse(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("배팅 금액은 숫자만 입력 가능합니다.");
    }


    @ParameterizedTest
    @ValueSource(strings = {"", "\n", "\t", " "})
    void 배팅금액_입력이_비어있으면_예외처리(String input) {
        assertThatThrownBy(() -> BettingAmountParser.parse(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("입력이 비어있습니다.");
    }
}