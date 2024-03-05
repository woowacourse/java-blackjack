package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class NameTest {

    @DisplayName("생성 테스트")
    @Test
    void create() {
        assertThatCode(() -> new Name("구름"))
                .doesNotThrowAnyException();
    }

    @DisplayName("사용자의 이름은 공백을 제외한 1글자 이상이다.")
    @ParameterizedTest
    @ValueSource(strings = {" ", "", "\r", "\n"})
    void validateNameSize(String source) {
        assertThatThrownBy(() -> new Name(source))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
