package blackjack.domain.player;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;

public class PlayerTest {

    @Test
    @DisplayName("플레이어의 이름이 null이 들어올 경우 예외가 발생해야 한다.")
    void createExceptionByNull() {
        assertThatThrownBy(() -> new Player(null))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("이름이 null이 들어올 수 없습니다.");
    }

    @ParameterizedTest
    @EmptySource
    @DisplayName("플레이어의 이름이 공백이 들어올 경우 예외가 발생해야 한다.")
    void createExceptionByBlank(final String input) {
        assertThatThrownBy(() -> new Player(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이름이 공백이 들어올 수 없습니다.");
    }
}
