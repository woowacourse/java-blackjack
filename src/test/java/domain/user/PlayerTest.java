package domain.user;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayerTest {
    @Test
    @DisplayName("플레이어면 true를 반환한다.")
    void isPlayer() {
        Player player = new Player(new Name("poby"));

        assertThat(player.isPlayer()).isTrue();
    }
}
