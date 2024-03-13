package blackjack.model.participants;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class NameTest {
    @DisplayName("이름이 널, 공백, 빈칸일 경우 예외가 발생한다.")
    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"  ", "    "})
    void validateName(String given) {
        assertThatThrownBy(() ->  new Name(given))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
