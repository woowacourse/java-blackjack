package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

public class PlayerTest {

    @ParameterizedTest
    @NullSource
    void createExceptionByNull(String input) {
        assertThatThrownBy(() -> new Player(input))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("[Error] 플레이어의 이름은 null이 들어올 수 없습니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " "})
    void createExceptionByEmpty(String input) {
        assertThatThrownBy(() -> new Player(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[Error] 플레이어의 이름은 공백이 들어올 수 없습니다.");
    }

}
