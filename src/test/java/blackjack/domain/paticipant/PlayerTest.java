package blackjack.domain.paticipant;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

class PlayerTest {

    @Test
    void 플레이어의_이름이_null인_경우_예외발생() {
        assertThatThrownBy(() -> new Player(null))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("이름은 null이 들어올 수 없습니다.");
    }
}
