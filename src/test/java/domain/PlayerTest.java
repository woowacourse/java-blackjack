package domain;

import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class PlayerTest {

    @DisplayName("이름은 5글자 이하이다")
    @ParameterizedTest
    @ValueSource(strings = {"a", "ash", "kiara", "woowa"})
    void validateNameLength(String name) {
        assertThatNoException().isThrownBy(() -> new Player(name));
    }

    @DisplayName("이름이 5글자 초과면 예외가 발생한다")
    @ParameterizedTest
    @ValueSource(strings = {"hijava", "helloworld", "woowacourse"})
    void nameNotOver5(String name) {
        assertThatThrownBy(() -> new Player(name))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("이름이 공백이면 예외가 발생한다")
    @ParameterizedTest
    @ValueSource(strings = {"", " "})
    void nameNotEmpty(String name) {
        assertThatThrownBy(() -> new Player(name))
            .isInstanceOf(IllegalArgumentException.class);
    }
}
