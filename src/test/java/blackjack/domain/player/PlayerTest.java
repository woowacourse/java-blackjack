package blackjack.domain.player;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.participant.GameStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;

public class PlayerTest {

    @Test
    @DisplayName("플레이어의 이름이 null이 들어올 경우 예외가 발생해야 한다.")
    void createExceptionByNull() {
        assertThatThrownBy(() -> new Player(null, GameStatus.RUNNING))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("이름이 null이 들어올 수 없습니다.");
    }

    @ParameterizedTest
    @EmptySource
    @DisplayName("플레이어의 이름이 공백이 들어올 경우 예외가 발생해야 한다.")
    void createExceptionByBlank(final String input) {
        assertThatThrownBy(() -> new Player(input, GameStatus.RUNNING))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이름이 공백이 들어올 수 없습니다.");
    }

    @Test
    @DisplayName("현재 상태가 종료되었는데 상태를 변경하면 예외가 발생해야 한다.")
    void changeGameStatusExceptionByFinished() {
        final Player player = new Player("player", GameStatus.FINISHED);
        assertThatThrownBy(() -> player.changeStatusFinished())
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("이미 종료된 게임은 종료요청을 할 수 없습니다.");
    }
}
