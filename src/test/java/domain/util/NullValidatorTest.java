package domain.util;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SuppressWarnings("NonAsciiCharacters")
public class NullValidatorTest {

    @ParameterizedTest
    @NullSource
    void null이_들어왔을때_예외발생확인_테스트(String input) {
        assertThatThrownBy(() -> NullValidator.validateNull(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("null이 들어왔습니다.");
    }
}
