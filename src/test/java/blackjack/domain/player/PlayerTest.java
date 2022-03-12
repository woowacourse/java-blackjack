package blackjack.domain.player;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayerTest {

    @Test
    @DisplayName("플레이어의 이름이 null이 들어올 경우 예외가 발생해야 한다.")
    void createExceptionByNull() {
        assertThatThrownBy(() -> new Player(null))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("이름이 null이 들어올 수 없습니다.");
    }
}
