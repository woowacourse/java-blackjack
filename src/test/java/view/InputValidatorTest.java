package view;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import util.InputValidator;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

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

}
