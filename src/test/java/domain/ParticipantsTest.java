package domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;
import vo.Name;

public class ParticipantsTest {
    public static final String ERROR_PREFIX = "[ERROR] ";

    @Test
    void 빈_참가자_이름_예외_테스트() {
        assertThatThrownBy(() -> new Name(""))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageStartingWith(ERROR_PREFIX);
    }

    @Test
    void 참가자_이름_숫자_예외_테스트() {
        assertThatThrownBy(() -> new Name("123"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageStartingWith(ERROR_PREFIX);
    }

    @Test
    void 쉼표_외의_특수문자_예외_테스트() {
        assertThatThrownBy(() -> new Name("영기:라이"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageStartingWith(ERROR_PREFIX);
    }
}
