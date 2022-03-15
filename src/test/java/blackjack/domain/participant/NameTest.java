package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class NameTest {

    @DisplayName("이름이 정상적으로 생성된다.")
    @Test
    void 이름_생성_정상() {
        assertDoesNotThrow(() -> new Name("mat"));
    }

    @DisplayName("이름은 공백이 아니어야한다.")
    @ParameterizedTest
    @ValueSource(strings = {"", " ", "   "})
    void 이름_공백_예외(String value) {
        assertThatThrownBy(() -> new Name(value))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이름은 공백이 아니어야합니다.");
    }
}
