package view;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import util.InputValidator;

import java.io.IOException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class InputViewTest {

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "phobi:jason", "마이찬,도우너", "phobii,jasonn"})
    void 이름_입력_실패_테스트(String input) throws IOException {
        assertThatThrownBy(() -> {
            InputValidator.validatePlayerNames(input);
        }).isInstanceOf(IllegalArgumentException.class);
    }
}
