package domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NameTest {
    // TODO: 나중에 커스텀 예외로 변경. 현재는 예외가 터지는 것만 검증됨.
    @Test
    @DisplayName("이름은 비어있거나 공백일 수 없다.")
    void validateBlank_InputBlank_ThrowsException() {
        String name = " ";
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Name(name);
        });
        assertEquals("이름은 비어있거나 공백일 수 없습니다.", exception.getMessage());
    }

    @Test
    @DisplayName("이름은 2글자 이상이어야한다.")
    void validateLength_InputOneLength_ThrowsException() {
        String name = "a";
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Name(name);
        });
        assertEquals("이름은 2~5글자만 허용됩니다.", exception.getMessage());
    }

    @Test
    @DisplayName("이름은 5글자 이하여야한다.")
    void validateLength_InputSixLength_ThrowsException() {
        String name = "abcdefg";
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Name(name);
        });
        assertEquals("이름은 2~5글자만 허용됩니다.", exception.getMessage());
    }
}
