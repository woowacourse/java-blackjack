package view;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import util.InputValidator;

public class InputValidatorTest {

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "phobi:jason", "마이찬,도우너", "phobii,jasonn"})
    void 이름_입력_실패_테스트(String input) {
        assertThatThrownBy(() -> {
            InputValidator.validatePlayerNames(input);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    // 크루 이름 성공 케이스
    @ParameterizedTest
    @ValueSource(strings = {"phobi,jason"})
    void 크루_이름_성공_케이스(String input) {
        assertThat(InputValidator.validatePlayerNames(input))
                .isTrue();
    }

    @Test
    void 베팅_금액_성공_테스트() {
        String betAmount = "20000";

        assertThatCode(() -> InputValidator.validateBetAmount(betAmount))
                .doesNotThrowAnyException();

    }

    @ParameterizedTest
    @ValueSource(strings = {"", " "})
    void 베팅_금액_실패_테스트(String input) {
        assertThatThrownBy(() -> InputValidator.validateBetAmount(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("입력 형식이 잘못됐습니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"99", "101"})
    void 베팅_금액_단위_실패_테스트(String input) {
        assertThatThrownBy(() -> InputValidator.validateBetAmount(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("베팅 금액은 10 단위로 입력해야 합니다.");
    }

}
