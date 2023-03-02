package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.NoSuchElementException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerCommandTest {

    @DisplayName("y를 입력하면 HIT, n을 입력하면 STAND로 변환할 수 있다.")
    @Test
    void convertCommandTest() {
        assertThat(PlayerCommand.from("y")).isEqualTo(PlayerCommand.HIT);
        assertThat(PlayerCommand.from("n")).isEqualTo(PlayerCommand.STAND);
        assertThatThrownBy(() -> PlayerCommand.from("깃짱"))
                .isInstanceOf(NoSuchElementException.class);
    }
}
