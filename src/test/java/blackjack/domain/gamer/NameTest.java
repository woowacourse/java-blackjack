package blackjack.domain.gamer;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class NameTest {

    @ParameterizedTest
    @ValueSource(strings = {"", " "})
    @DisplayName("빈문자열일 경우 예외를 발생시킨다.")
    void validateName(String source) {
        Assertions.assertThatThrownBy(() -> {
                    new Name(source);
                }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("이름은 공백이 될 수 없습니다.");
    }
}
