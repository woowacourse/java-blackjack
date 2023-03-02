package domain;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class NameTest {
    @DisplayName("이름의 길이는 1글자 미만 15글자 초과일 경우 예외처리한다.")
    @ParameterizedTest
    @ValueSource(strings = {"", "1234567890123456", "12345678901234567"})
    void validateLengthTest(String name) {
        assertThrows(IllegalArgumentException.class, () -> new Name(name));
    }
}
