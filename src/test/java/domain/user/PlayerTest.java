package domain.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayerTest {
    @Test
    @DisplayName("Player 생성")
    void create() {
        assertThat(new Player("이름")).isInstanceOf(Player.class);
    }
}
