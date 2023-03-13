package domain.player;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class NameTest {

    @DisplayName("이름의 길이는 1글자 미만 15글자 초과일 경우 예외처리한다.")
    @ParameterizedTest
    @ValueSource(strings = {"", "1234567890123456", "12345678901234567"})
    void validateLengthTest(String name) {
        assertThatThrownBy(() -> new Name(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR]: 이름은 1자이상 15자이하로 입력해주세요.");
    }
}
