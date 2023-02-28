package player;

import static org.assertj.core.api.Assertions.assertThatCode;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NameTest {
    @Test
    @DisplayName("Name은 이름 문자열을 받아 생성된다.")
    void create() {
        assertThatCode(() -> new Name("폴로"))
                .doesNotThrowAnyException();
    }
}
