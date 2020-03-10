package domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayerTest {
    @Test
    void create() {
        assertThat(new Player("이름")).isInstanceOf(Player.class);
    }
}
