package domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NameTest {

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
    @DisplayName("이름은 영어만 허용된다.")
    void validateEnglish_InputKorean_ThrowsException() {
        String name = "한글";
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Name(name);
        });
        assertEquals("이름은 영어만 허용됩니다.", exception.getMessage());
    }

    @Test
    @DisplayName("이름은 2글자 이상이어야한다.")
    void validateBlank_InputOneLength_ThrowsException() {
        String name = "a";
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Name(name);
        });
        assertEquals("이름은 2~5글자만 허용됩니다.", exception.getMessage());
    }

    @Test
    @DisplayName("이름은 5글자 이하여야한다.")
    void validateBlank_InputSixLength_ThrowsException() {
        String name = "abcdefg";
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Name(name);
        });
        assertEquals("이름은 2~5글자만 허용됩니다.", exception.getMessage());
    }
}
