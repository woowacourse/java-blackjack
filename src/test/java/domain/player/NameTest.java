package domain.player;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NameTest {
    @Test
    @DisplayName("이름 영어, 글자 수, 이름 내 공백 여부 테스트")
    void 이름_영어_글자수_공백미포함_검증() {
        // then
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Name("123456"));
    }
}