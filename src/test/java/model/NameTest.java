package model;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class NameTest {

    @ParameterizedTest
    @DisplayName("눈에 보이지 않는 공백들로 이루어진 생성은 예외를 발생한다.")
    @ValueSource(strings = {"", " "})
    void validate_ShouldThrowException_WhenInputStringHasBlank(String value) {
        assertThrows(IllegalArgumentException.class, () -> new Name(value));
    }

    @ParameterizedTest
    @DisplayName("공백이 포함된 문자열을 이용한 생성은 예외를 발생한다.")
    @ValueSource(strings = {"d obby", "j o y", "도  비"})
    void validate_ShouldThrowException_WhenInputStringContainsBlank(String value) {
        assertThrows(IllegalArgumentException.class, () -> new Name(value));
    }

}
