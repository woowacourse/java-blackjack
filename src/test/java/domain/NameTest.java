package domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NameTest {

    @Test
    @DisplayName("이름은 비어있거나 공백일 수 없다.")
    void validateBlank_InputBlank_ThrowsException() {
        String name = " ";
        assertThrows(IllegalArgumentException.class, () -> {
            new Name(name);
        });
    }

    @Test
    @DisplayName("이름은 영어만 허용된다.")
    void validateEnglish_InputKorean_ThrowsException() {
        String name = "한글";
        assertThrows(IllegalArgumentException.class, () -> {
            new Name(name);
        });
    }

    @Test
    @DisplayName("이름은 2글자 이상이어야한다.")
    void validateBlank_InputOneLength_ThrowsException() {
        String name = "1";
        assertThrows(IllegalArgumentException.class, () -> {
            new Name(name);
        });
    }

    @Test
    @DisplayName("이름은 5글자 이하여야한다.")
    void validateBlank_InputSixLength_ThrowsException() {
        String name = "123456";
        assertThrows(IllegalArgumentException.class, () -> {
            new Name(name);
        });
    }
}
