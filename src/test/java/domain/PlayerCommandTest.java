package domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerCommandTest {

    @DisplayName("y를 입력하면 HIT, n을 입력하면 STAND로 변환할 수 있다.")
    @Test
    void convertCommandTest() {
        assertThat(PlayerCommand.from(true)).isEqualTo(PlayerCommand.HIT);
        assertThat(PlayerCommand.from(false)).isEqualTo(PlayerCommand.STAND);
    }
}
