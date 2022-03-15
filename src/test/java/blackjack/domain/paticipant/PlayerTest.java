package blackjack.domain.paticipant;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class PlayerTest {

    @Test
    void 플레이어의_이름이_null인_경우_예외발생() {
        assertThatThrownBy(() -> new Player(null, 1000))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("이름은 null이 들어올 수 없습니다.");
    }

    @ParameterizedTest
    @EmptySource
    void 플레이어의_이름이_공백인_경우_예외발생(final String name) {
        assertThatThrownBy(() -> new Player(name, 1000))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이름은 공백이 들어올 수 없습니다.");
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -1000})
    void 배팅금액이_0이하인_경우_예외발생(final int betMoney) {
        assertThatThrownBy(() -> new Player("name", betMoney))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("배팅금액은 0이하의 값이 들어올 수 없습니다.");
    }
}
