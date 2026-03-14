package domain;

import static org.junit.jupiter.api.Assertions.*;

import domain.participant.Name;
import org.junit.jupiter.api.Test;

class NameTest {
    // TODO: 나중에 커스텀 예외로 변경. 현재는 예외가 터지는 것만 검증됨.
    @Test
    void 이름이_비어있거나_공백이면_예외가_발생한다() {
        String name = " ";
        assertThrows(IllegalArgumentException.class, () -> {
            new Name(name);
        });
    }

    @Test
    void 이름이_2글자_미만이면_예외가_발생한다() {
        String name = "a";
        assertThrows(IllegalArgumentException.class, () -> {
            new Name(name);
        });
    }

    @Test
    void 이름이_5글자를_초과하면_예외가_발생한다() {
        String name = "abcdefg";
        assertThrows(IllegalArgumentException.class, () -> {
            new Name(name);
        });
    }
}
