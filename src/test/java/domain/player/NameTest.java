package domain.player;

import domain.player.attribute.Name;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NameTest {
    @Test
    @DisplayName("이름 영어, 글자 수, 이름 내 공백 여부 테스트")
    void 이름_영어_글자수_공백미포함_검증() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Name("123456"));
    }

    @Test
    @DisplayName("이름 한 글자면 에러 발생 테스트")
    void 이름_한_글자수_검증() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Name("a"));
    }

    @Test
    @DisplayName("이름 10글자 초과면 에러 발생 테스트")
    void 이름_열_글자수_검증() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Name("abcdefghijk"));
    }
}