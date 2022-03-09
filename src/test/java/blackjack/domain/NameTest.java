package blackjack.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NameTest {

    @Test
    @DisplayName("Name 클래스는 이름을 입력받으면 정상적으로 생성된다.")
    void create_name() {
        assertThatCode(() -> new Name("aki")).doesNotThrowAnyException();
    }
}
