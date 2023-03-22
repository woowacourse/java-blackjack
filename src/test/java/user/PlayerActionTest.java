package user;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerActionTest {

    @DisplayName("y를 입력받으면 Hit을 반환해준다.")
    @Test
    void getActionByYes() {
        Assertions.assertThat(PlayerAction.getActionByInput("y")).isEqualTo(PlayerAction.HIT);
    }

    @DisplayName("n를 입력받으면 Stand을 반환해준다.")
    @Test
    void getActionByNo() {
        Assertions.assertThat(PlayerAction.getActionByInput("n")).isEqualTo(PlayerAction.STAND);
    }

    @DisplayName("y,n이 아닌 입력으로 생성시 오류를 던진다.")
    @Test
    void getActionByInvalidInput() {
        Assertions.assertThatThrownBy(() -> PlayerAction.getActionByInput("k"))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("y나 n으로 입력해 주세요.");
    }
}
