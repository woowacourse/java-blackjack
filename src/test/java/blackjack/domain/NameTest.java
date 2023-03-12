package blackjack.domain;

import blackjack.domain.player.Name;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class NameTest {
    @ParameterizedTest
    @DisplayName("유효성 검증 확인 - 이름에 공백만 들어가는 경우")
    @ValueSource(strings = {"", "   ", "       "})
    void blankNameError(String input) {
        Assertions.assertThatThrownBy(() -> new Name(input)).isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @DisplayName("유효성 검증 확인 - 성공")
    @ValueSource(strings = {"io", "jena", "pobi"})
    void blankNameSuccess(String input) {
        assertDoesNotThrow(() -> new Name(input));
    }
}
